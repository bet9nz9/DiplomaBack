package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.db.annotations.Attr;
import com.diploma.edu.source.db.annotations.Processor;
import com.diploma.edu.source.model.BaseEntity;
import com.diploma.edu.source.model.ListValues;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsertRequestBuilder<T extends BaseEntity> {

    private String objectsInsert = "INSERT INTO OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES ({0}, {1}, {2}, {3}, {4})";
    private String referencesInsert = "INSERT INTO OBJREFERENCE(attr_id, reference, object_id) VALUES ({0}, {1}, {2})";
    private String attributeInsert = "INSERT INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES ({0}, {1}, {2}, {3}, {4})";


    public List<String> getInsertStatements(T object) throws IllegalAccessException {
        List<String> statements = new ArrayList<>();
        List<Attr> attributes = Processor.getAttributes(object.getClass());

        statements.add(MessageFormat.format(objectsInsert, object.getId(),
                null,
                Processor.getObjtypeId(object.getClass()),
                object.getName() == null ? null : "'" + object.getName() + "'",
                object.getDescription() == null ? null : "'" + object.getDescription() + "'"));

        for (Attr attr : attributes) {
            attr.field.setAccessible(true);
            Object value = attr.field.get(object);
            if (value == null) {
                continue;
            }

            switch (attr.valueType) {
                case DATE_VALUE:
                    statements.add(MessageFormat.format(attributeInsert, attr.id,
                            object.getId(),
                            null,
                            BigInteger.valueOf(((Date) value).getTime()).toString(),
                            null));
                    continue;
                case LIST_VALUE:
                    statements.add(MessageFormat.format(attributeInsert, attr.id,
                            object.getId(),
                            null,
                            null,
                            ListValues.getListValueIdByValue(value.toString())));
                    continue;
                case REF_VALUE:
                    statements.add(MessageFormat.format(referencesInsert, attr.id,
                            ((BaseEntity) value).getId(),
                            object.getId()));
                    continue;
                case VALUE:
                    statements.add(MessageFormat.format(attributeInsert, attr.id,
                            object.getId(),
                            value.toString() == null ? null : "'" + value.toString() + "'",
                            null,
                            null));
                    continue;
                default:
                    continue;
            }
        }
        return statements;
    }

}
