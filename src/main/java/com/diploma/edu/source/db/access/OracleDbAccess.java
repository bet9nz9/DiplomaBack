package com.diploma.edu.source.db.access;

import com.diploma.edu.source.db.annotations.Attr;
import com.diploma.edu.source.db.annotations.Processor;
import com.diploma.edu.source.db.annotations.ValueType;
import com.diploma.edu.source.exceptions.IncorrectDataException;
import com.diploma.edu.source.model.BaseEntity;
import com.diploma.edu.source.servicies.requestBuilder.*;
import com.diploma.edu.source.servicies.requestBuilder.criteria.PaginationCriteria;
import com.diploma.edu.source.servicies.requestBuilder.mappers.*;
import com.diploma.edu.source.servicies.requestBuilder.preparedRequests.DeletePreparedRequests;
import com.diploma.edu.source.servicies.requestBuilder.preparedRequests.SelectPreparedRequests;
import lombok.SneakyThrows;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Component
public class OracleDbAccess implements DbAccess {
    private static final Logger logger = Logger.getLogger(OracleDbAccess.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @SneakyThrows
    public <T extends BaseEntity> int update(T obj) {
        T objectFromDataBase = (T) getById(obj.getClass(), obj.getId());
        List<String> statements = new UpdateRequestBuilder<T>().getUpdateStatements(objectFromDataBase, obj);
        if (statements.isEmpty()){
            throw new IncorrectDataException("Нет изменений!");
        }

        String[] str = new String[0];
        logger.log(Level.INFO, "Update statements: " + statements);
        try{
            jdbcTemplate.batchUpdate(statements.toArray(str));
            return 1;
        } catch (RuntimeException e){
            return 0;
        }
    }

    @Override
    @SneakyThrows
    public <T extends BaseEntity> int insert(T obj) {
        obj.setId(jdbcTemplate.queryForObject(SelectPreparedRequests.GET_NEW_OBJECT_ID.getRequest(), BigInteger.class));

        if (!isUnique(obj.getId())) {
            return -1;
        }

        List<String> statements = new InsertRequestBuilder<T>().getInsertStatements(obj);

        String[] str = new String[0];

        logger.log(Level.INFO, "Insert statements");

        try{
            jdbcTemplate.batchUpdate(statements.toArray(str));
            return 1;
        } catch (RuntimeException e){
            return 0;
        }
    }

    private boolean isUnique(BigInteger id) {
        return jdbcTemplate.queryForList(MessageFormat.format(SelectPreparedRequests.GET_OBJECT_ID.getRequest(), id)).isEmpty();
    }

    @Override
    public <T extends BaseEntity> Integer delete(Class<T> clazz, BigInteger id) {
        return jdbcTemplate.update(MessageFormat.format(DeletePreparedRequests.DELETE_OBJECT.getRequest(), id));
    }

    /**
     * Принимаем все критерии, которые могут быть, в requestBuilder все предусмотрено, что бы запрос создался адекватно
     * но могут быть проблемы со специальными запросами, хотя они вряд ли понадобятся.
     * <p>
     * Создается director в его конструктор необходимо передать класс, с корорым мы сейчас работаем.
     * Для получения запроса у директора вызывается метод buildRequest, в сигнатуру которого необходимо передать все параметры,
     * несмотря на нулы, внутри все проверяется.
     * <p>
     * Вывозв toString строит запрос из 4 частей select, from, where, filter блоки, они описаны внутри класса Request.
     * После получения строки запроса вызывается метод selectAll, который возвращает список необходимых элементов,
     * после чего этот список необходимо передать в PageImpl, который уже построит страницу
     */
    @Override
    public <T extends BaseEntity> Page<T> selectPage(Class<T> clazz, Map<String, String> params) {

        Pageable pageable = PaginationCriteria.getPagination(params);

        List<T> resultElements = selectAll(clazz, Director.valueOf(clazz).
                getRequest(params).
                buildRequest());
        Long countOfElements = selectCountOfFilterElements(
                Director.valueOf(clazz).
                        getRequest(new CountElementsRequest(new Request(clazz), params)).
                        buildRequest());
        if (pageable == null || resultElements.isEmpty()) {
            return new PageImpl<>(resultElements);
        }
        if (resultElements.size() != pageable.getPageSize()) {
            pageable = PageRequest.of(pageable.getPageNumber(), resultElements.size());
        }
        return new PageImpl<>(resultElements, pageable, countOfElements);
    }

    private <T extends BaseEntity> List<T> selectAll(Class<T> clazz, String request) {
        logger.log(Level.INFO, "Request: " + request);
        List<Attr> attributes = Processor.getAttributes(clazz);
        return jdbcTemplate.query(request, new RowMapper<T>() {
            @SneakyThrows
            @Override
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                T object = clazz.getDeclaredConstructor().newInstance();
                for (Attr attr : attributes) {
                    attr.getField().setAccessible(true);
                    Object value = rs.getObject(attr.getField().getName());
                    if (attr.getValueType().equals(ValueType.LIST_VALUE)) {
                        new MapperDirector(new BooleanValueMapper(attr)).mapObjectAttribute(object, getListValueById(value));
                    } else if (attr.getValueType().equals(ValueType.REF_VALUE)) {
                        BigInteger referencedObjId = getReferencedObjectId(rs.getObject("id"), attr.getId());
                        new MapperDirector(new ReferenceMapper(attr)).mapObjectAttribute(object, getById(attr.getClazz(), referencedObjId));
                    } else if (attr.getValueType().equals(ValueType.DATE_VALUE)) {
                        new MapperDirector(new DateValueMapper(attr)).mapObjectAttribute(object, value);
                    } else {
                        new MapperDirector(new AttributeMapper(attr)).mapObjectAttribute(object, value);
                    }
                }
                return object;
            }
        });
    }

    /**
     * При использовании специальных запросов, например как getById или countElements, необходимо создать директора,
     * после чего при візове метода buildRequest передать ссілку на необходимій билдер, внутрь которого передать параметры.
     */

    @Override
    public <T extends BaseEntity> T getById(Class<T> clazz, BigInteger id) {
        List<T> result = selectAll(clazz, Director.valueOf(clazz).
                getRequest(new RequestGetByID(new Request(clazz), id)).
                buildRequest());
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    /**
     * Получает цифру - общее количество элементов, которые соответсвуют фильтру.
     * Если фильтра нет, то просто возвращается общее количество элементов БД
     */
    private Long selectCountOfFilterElements(String request) {
        List<Long> list = jdbcTemplate.queryForList(request, Long.class);
        return list.get(0);
    }

    private BigInteger getReferencedObjectId(Object objectId, Object attrId) {
        try {
            return jdbcTemplate.queryForObject(MessageFormat.format(SelectPreparedRequests.GET_REFERENCED_OBJ_ID.getRequest(), attrId, objectId), BigInteger.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private String getListValueById(Object listValueId) {
        if (listValueId == null) {
            return null;
        }
        return jdbcTemplate.queryForObject(MessageFormat.format(SelectPreparedRequests.GET_LIST_VALUE_BY_ID.getRequest(), listValueId), String.class);
    }

    public List<String> getEmails() {

        return jdbcTemplate.queryForList(SelectPreparedRequests.GET_EMAILS.getRequest(), String.class);
    }

    public BigInteger getAllNotesById(BigInteger categoryId) {

        return jdbcTemplate.queryForObject(MessageFormat.format(SelectPreparedRequests.IS_CATEGORY_WITHOUT_NOTIFICATIONS.getRequest(), categoryId), BigInteger.class);
    }
}
