package com.diploma.edu.source;

import com.diploma.edu.source.db.annotations.Attr;
import com.diploma.edu.source.db.annotations.Processor;
import com.diploma.edu.source.db.annotations.ValueType;
import com.diploma.edu.source.model.BaseEntity;
import com.diploma.edu.source.model.User;
import com.diploma.edu.source.servicies.requestBuilder.mappers.AttributeMapper;
import com.diploma.edu.source.servicies.requestBuilder.mappers.BooleanValueMapper;
import com.diploma.edu.source.servicies.requestBuilder.mappers.MapperDirector;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

@SpringBootTest
public class TestConnectionToDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder encoder;

    @Test
    void testConnection() {
        Class<? extends BaseEntity> clazz = User.class;
        String request = "select * from (\n" +
                "    SELECT o.object_id as id,\n" +
                "       o.name as name,\n" +
                "       o.description as description,\n" +
                "       a0.VALUE as email,\n" +
                "       a1.VALUE as password,\n" +
                "       a2.VALUE as firstName,\n" +
                "       a3.VALUE as lastName,\n" +
                "       a4.VALUE as patronymic,\n" +
                "       a5.VALUE as isActive,\n" +
                "       a6.VALUE as receiveUtilityNotification,\n" +
                "       a7.REFERENCE as role,\n" +
                "       a8.VALUE as activationCode\n" +
                "    FROM OBJECTS o\n" +
                "         left join ATTRIBUTES a0 on o.object_id = a0.object_id and a0.attr_id = 21\n" +
                "         left join ATTRIBUTES a1 on o.object_id = a1.object_id and a1.attr_id = 22\n" +
                "         left join ATTRIBUTES a2 on o.object_id = a2.object_id and a2.attr_id = 23\n" +
                "         left join ATTRIBUTES a3 on o.object_id = a3.object_id and a3.attr_id = 24\n" +
                "         left join ATTRIBUTES a4 on o.object_id = a4.object_id and a4.attr_id = 25\n" +
                "         left join ATTRIBUTES a5 on o.object_id = a5.object_id and a5.attr_id = 26\n" +
                "         left join ATTRIBUTES a6 on o.object_id = a6.object_id and a6.attr_id = 27\n" +
                "         left join OBJREFERENCE a7 on o.object_id = a7.object_id and a7.attr_id = 28\n" +
                "         left join ATTRIBUTES a8 on o.object_id = a8.object_id and a8.attr_id = 55\n" +
                "    WHERE o.OBJECT_TYPE_ID = 8)\n" +
                "where id = 11";

        String request2 = "SELECT o.object_id as id,\n" +
                "       o.name as name,\n" +
                "       o.description as description,\n" +
                "       a0.VALUE as email,\n" +
                "       a1.VALUE as password,\n" +
                "       a2.VALUE as firstName,\n" +
                "       a3.VALUE as lastName,\n" +
                "       a4.VALUE as patronymic,\n" +
                "       a5.VALUE as isActive,\n" +
                "       a6.VALUE as receiveUtilityNotification,\n" +
                "       a7.REFERENCE as role,\n" +
                "       a8.VALUE as activationCode\n" +
                "FROM OBJECTS o\n" +
                "         left join ATTRIBUTES a0 on o.object_id = a0.object_id and a0.attr_id = 21\n" +
                "         left join ATTRIBUTES a1 on o.object_id = a1.object_id and a1.attr_id = 22\n" +
                "         left join ATTRIBUTES a2 on o.object_id = a2.object_id and a2.attr_id = 23\n" +
                "         left join ATTRIBUTES a3 on o.object_id = a3.object_id and a3.attr_id = 24\n" +
                "         left join ATTRIBUTES a4 on o.object_id = a4.object_id and a4.attr_id = 25\n" +
                "         left join ATTRIBUTES a5 on o.object_id = a5.object_id and a5.attr_id = 26\n" +
                "         left join ATTRIBUTES a6 on o.object_id = a6.object_id and a6.attr_id = 27\n" +
                "         left join OBJREFERENCE a7 on o.object_id = a7.object_id and a7.attr_id = 28\n" +
                "         left join ATTRIBUTES a8 on o.object_id = a8.object_id and a8.attr_id = 55\n" +
                "    WHERE o.OBJECT_TYPE_ID = 8";

        String selectObjects = "select OBJECT_ID as id, NAME, DESCRIPTION from OBJECTS";

        String selectDate = "select * from ATTRIBUTES where ATTR_ID = 33";

        List<Object> result = getObjectsWithMapper(clazz, request2);
        System.out.println();
    }

    private List<Object> getObjectsWithMapper(Class<? extends BaseEntity> clazz, String request2) {
        List<Attr> attrList = Processor.getAttributes(clazz);

        return jdbcTemplate.query(request2, new RowMapper<Object>() {
            @SneakyThrows
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                Object object = clazz.getDeclaredConstructor().newInstance();
                for (Attr attr : attrList){
                    attr.field.setAccessible(true);
                    Object value = rs.getObject(attr.field.getName());
                    if (attr.valueType.equals(ValueType.LIST_VALUE)){
                        new MapperDirector(new BooleanValueMapper(attr)).mapObjectAttribute(object, value);
                    } else if (attr.valueType.equals(ValueType.REF_VALUE)){
                        Long referencedObjId = getReferencedObjectId(rs.getObject("id"), attr.id);
                        System.out.println();
                    } else {
                        new MapperDirector(new AttributeMapper(attr)).mapObjectAttribute(object, value);
                    }
                }
                return object;
            }
        });
    }

    Long getReferencedObjectId(Object objectId, Object attrId){
        String request = MessageFormat.format("SELECT reference FROM OBJREFERENCE WHERE attr_id = {0} and object_id = {1}", attrId , objectId);
        List<Long> result = jdbcTemplate.query(request, new RowMapper<Long> () {
            @Override
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong("reference");
            }
        });

        if (result.isEmpty()){
            return null;
        }
        return result.get(0);
    }


}
