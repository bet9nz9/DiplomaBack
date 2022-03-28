package com.diploma.edu.source.db.access;

import com.diploma.edu.source.db.annotations.Attr;
import com.diploma.edu.source.db.annotations.Processor;
import com.diploma.edu.source.db.annotations.ValueType;
import com.diploma.edu.source.model.BaseEntity;
import com.diploma.edu.source.model.Notification;
import com.diploma.edu.source.servicies.requestBuilder.CountElementsRequest;
import com.diploma.edu.source.servicies.requestBuilder.Director;
import com.diploma.edu.source.servicies.requestBuilder.Request;
import com.diploma.edu.source.servicies.requestBuilder.RequestGetByID;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import com.diploma.edu.source.servicies.requestBuilder.mappers.*;
import lombok.SneakyThrows;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class OracleDbAccess implements DbAccess {
    private static final Logger logger = Logger.getLogger(OracleDbAccess.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public <T extends BaseEntity> int update(T obj) {
        Long objId = obj.getId();
        if (isUnique(objId)) {
            return -1;
        }
        ArrayList<String> statements = new ArrayList<>();
        try {
            List<Attr> attributes = Processor.getAttributes(obj.getClass());
            statements.add("UPDATE OBJECTS SET name = '" + obj.getName() + "', description = '" + obj.getDescription()
                    + "' WHERE object_id = " + objId);

            for (int i = 0; i < attributes.size(); i++) {
                attributes.get(i).field.setAccessible(true);
                if (attributes.get(i).valueType == ValueType.BASE_VALUE) {
                    continue;
                }
                if (attributes.get(i).valueType == ValueType.LIST_VALUE) {
                    List<Long> list = (List<Long>) attributes.get(i).field.get(obj);
                    for (int j = 0; j < list.size(); j++) {
                        statements.add(getDeleteStatement(attributes.get(i), objId));
                        statements.add(getInsertStatement(attributes.get(i), objId, list.get(j)));
                    }
                } else {
                    statements.add(getUpdateStatement(attributes.get(i), objId, attributes.get(i).field.get(obj)));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 1;
        }
        String[] str = new String[0];
        logger.log(Level.INFO, "Update statements: " + statements);
        jdbcTemplate.batchUpdate(statements.toArray(str));
        return 0;
    }

    @Override
    public <T extends BaseEntity> int insert(T obj) {

        Long id = jdbcTemplate.queryForList("select OBJECTS_SEQ.NEXTVAL from dual ", Long.class).get(0);

//        Long objId = obj.getId();
//        if (!isUnique(objId)) {
//            return -1;
//        }

        ArrayList<String> statements = new ArrayList<>();
        try {
            List<Attr> attributes = Processor.getAttributes(obj.getClass());
            statements.add("\nINSERT INTO OBJECTS (object_id, name, description, object_type_id) VALUES ('"
                    + id + "', '" + obj.getName() + "', '" + obj.getDescription() + "', '"
                    + Processor.getObjtypeId(obj.getClass()) + "')");

            for (int i = 0; i < attributes.size(); i++) {
                attributes.get(i).field.setAccessible(true);
                if (attributes.get(i).valueType == ValueType.BASE_VALUE) {
                    continue;
                }
                if (attributes.get(i).valueType == ValueType.LIST_VALUE) {
                    List<Long> list = (List<Long>) attributes.get(i).field.get(obj);
                    for (int j = 0; j < list.size(); j++) {
                        statements.add(getInsertStatement(attributes.get(i), id, list.get(j)));
                    }
                } else {
                    statements.add(getInsertStatement(attributes.get(i), id, attributes.get(i).field.get(obj)));
                    if (attributes.get(i).valueType.getTable() == "OBJREFERENCE") {
                        statements.add(getOBJREFStatement(attributes.get(i), id, attributes.get(i).field.get(obj)));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            logger.log(Level.WARN, e);
            e.printStackTrace();
            return 1;
        }
        String[] str = new String[0];

        for (int i = 0; i < statements.size(); i++) {
            if (statements.get(i) == null) {
                statements.remove(i);
            }
        }

        logger.log(Level.INFO, "Insert statements");

        jdbcTemplate.batchUpdate(statements.toArray(str));
        return 0;
    }

    private boolean isUnique(Long id) {
        if (jdbcTemplate.queryForList("SELECT object_id FROM objects WHERE object_id =" + id).isEmpty())
            return true;
        return false;
    }

    @Override
    public <T extends BaseEntity> Integer delete(Class<T> clazz, Long id) {
        int objTypeId = Processor.getObjtypeId(clazz);
        return jdbcTemplate.update("DELETE FROM OBJECTS WHERE OBJECT_ID = " + id);
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
    public <T extends BaseEntity> Page<T> selectPage(Class<T> clazz, Pageable pageable, List<SearchCriteria> filter, SortCriteria sort) {
        logger.log(Level.INFO, "Request parameters: \n"
                + "Pageable - " + pageable
                + "\n Filter - " + filter
                + "\n Sort - " + sort);

        List<T> resultElements = selectAll(clazz, Director.valueOf(clazz).
                getRequest(pageable, filter, sort).
                buildRequest());
        Long countOfElements = selectCountOfFilterElements(
                Director.valueOf(clazz).
                        getRequest(new CountElementsRequest(new Request(clazz), filter, sort)).
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
        List<T> list = jdbcTemplate.query(request, new RowMapper<T>() {
            @SneakyThrows
            @Override
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                T object = clazz.getDeclaredConstructor().newInstance();
                for (Attr attr : attributes) {
                    attr.field.setAccessible(true);
                    Object value = rs.getObject(attr.field.getName());
                    if (attr.valueType.equals(ValueType.LIST_VALUE)) {
                        new MapperDirector(new BooleanValueMapper(attr)).mapObjectAttribute(object, getListValueById(value));
                    } else if (attr.valueType.equals(ValueType.REF_VALUE)) {
                        Long referencedObjId = getReferencedObjectId(rs.getObject("id"), attr.id);
                        new MapperDirector(new ReferenceMapper(attr)).mapObjectAttribute(object, getById(attr.clazz, referencedObjId));
                    } else if (attr.valueType.equals(ValueType.DATE_VALUE)) {
                        new MapperDirector(new DateValueMapper(attr)).mapObjectAttribute(object, value);
                    } else {
                        new MapperDirector(new AttributeMapper(attr)).mapObjectAttribute(object, value);
                    }
                }
                return object;
            }
        });
        return list;
    }

    /**
     * При использовании специальных запросов, например как getById или countElements, необходимо создать директора,
     * после чего при візове метода buildRequest передать ссілку на необходимій билдер, внутрь которого передать параметры.
     */

    @Override
    public <T extends BaseEntity> T getById(Class<T> clazz, Long id) {
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

    private Long getReferencedObjectId(Object objectId, Object attrId) {
        String request = MessageFormat.format("SELECT reference FROM OBJREFERENCE WHERE attr_id = {0} and object_id = {1}", attrId, objectId);
        return jdbcTemplate.queryForObject(request, Long.class);
    }

    private String getListValueById(Object listValueId){
        String request = MessageFormat.format("SELECT VALUE FROM LISTS WHERE LIST_VALUE_ID = {0}", listValueId);
        return jdbcTemplate.queryForObject(request, String.class);
    }

    private List<Long> getListForObjectAttribute(Attr attr, Long objectId) {
        String sql = "SELECT reference \"id\" FROM objreference WHERE attr_id = " + attr.id +
                " AND object_id = " + objectId;
        return jdbcTemplate.queryForList(sql, Long.class);
    }

    public List<String> getEmails() {
        String sql = "SELECT * FROM ( SELECT\n" +
                "                       listagg(a0.VALUE) \"email\"\n" +
                "                FROM OBJECTS o\n" +
                "                         left join ATTRIBUTES a0 on o.object_id = a0.object_id and a0.attr_id = 21\n" +
                "                WHERE o.object_type_id = 10\n" +
                "                group by o.object_id\n" +
                "              ) WHERE 1=1";

        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<Notification> getAllNotesById(int categoryId) {
        String sql = "SELECT * FROM ( SELECT o.object_id \"id\",\n" +
                "                       listagg(o.name) \"name\",\n" +
                "                       listagg(o.description) \"description\" ,\n" +
                "                       listagg(a0.VALUE) \"text\",\n" +
                "                       to_date(listagg(to_char(a1.DATE_VALUE, 'yyyy-mm-dd hh24:mi:ss')), 'yyyy-mm-dd hh24:mi:ss') \"date\",\n" +
                "                       listagg(a2.VALUE) \"title\",\n" +
                "                       listagg(a3.REFERENCE) \"category\",\n" +
                "                       listagg(a4.REFERENCE) \"createdBy\"\n" +
                "                FROM OBJECTS o\n" +
                "                         left join ATTRIBUTES a0 on o.object_id = a0.object_id and a0.attr_id = 32\n" +
                "                         left join ATTRIBUTES a1 on o.object_id = a1.object_id and a1.attr_id = 33\n" +
                "                         left join ATTRIBUTES a2 on o.object_id = a2.object_id and a2.attr_id = 34\n" +
                "                         left join OBJREFERENCE a3 on o.object_id = a3.object_id and a3.attr_id = 35\n" +
                "                         left join OBJREFERENCE a4 on o.object_id = a4.object_id and a4.attr_id = 36\n" +
                "                WHERE o.object_type_id = 13\n" +
                "                group by o.object_id\n" +
                "              ) where \"category\" = " + categoryId + "";
        return jdbcTemplate.queryForList(sql, Notification.class);
    }

    private String getInsertStatement(Attr attr, Long objectId, Object value) {
        String newValue = "'" + value + "'";
        if (value == null) {
            newValue = null;
            if (attr.valueType.getTable() == "OBJREFERENCE") {
                return null;
            }
        }
        if (attr.valueType.getTable() == "OBJREFERENCE") {
            BaseEntity baseEntity = (BaseEntity) value;
            return "\nINSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE) VALUES" + " (" + attr.id + ", " + objectId + ", "
                    + baseEntity.getId().toString() + ")";
        }
        if (attr.valueType.getValueType().equals("DATE_VALUE")) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newValue = dateFormat.format(value);
            return "\nINSERT INTO " + attr.valueType.getTable() + " (ATTR_ID, OBJECT_ID, "
                    + attr.valueType.getValueType() + ") VALUES" + " (" + attr.id + ", " + objectId + ", (TO_DATE('"
                    + newValue + "', 'yyyy-mm-dd hh24:mi:ss')))";
        } else {
            return "\nINSERT INTO " + attr.valueType.getTable() + " (ATTR_ID, OBJECT_ID, "
                    + attr.valueType.getValueType() + ") VALUES" + " (" + attr.id + ", " + objectId + ", "
                    + newValue + ")";
        }
    }

    private String getOBJREFStatement(Attr attr, Long objectId, Object value) {
        BaseEntity baseEntity = (BaseEntity) value;
        return "\nINSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE)" +
                " VALUES (" + attr.id + ", " + objectId + ", " + baseEntity.getId() + ")";
    }

    private String getDeleteStatement(Attr attr, Long objectId) {
        return "DELETE FROM " + attr.valueType.getTable() + " WHERE attr_id = " + attr.id + " AND object_id = "
                + objectId;
    }

    private String getUpdateStatement(Attr attr, Long objectId, Object value) {
        String newValue = "'" + value + "'";
        if (value == null) {
            newValue = null;
        }
        if (attr.valueType == ValueType.REF_VALUE) {
            BaseEntity baseEntity = (BaseEntity) value;
            return "UPDATE " + attr.valueType.getTable() + " SET " + attr.valueType.getValueType() + " = " + baseEntity.getId()
                    + " WHERE attr_id = " + attr.id + " AND object_id = " + objectId;
        }
        if (attr.valueType == ValueType.DATE_VALUE) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newValue = dateFormat.format(value);
            return "\nUPDATE " + attr.valueType.getTable() + " SET " + attr.valueType.getValueType() + " = to_date('" + newValue
                    + "', 'yyyy-mm-dd hh24:mi:ss') WHERE attr_id = " + attr.id + " AND object_id = " + objectId;
        } else {
            return "\nUPDATE " + attr.valueType.getTable() + " SET " + attr.valueType.getValueType() + " = " + newValue
                    + " WHERE attr_id = " + attr.id + " AND object_id = " + objectId;
        }
    }
}
