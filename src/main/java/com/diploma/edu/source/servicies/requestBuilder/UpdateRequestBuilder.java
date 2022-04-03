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

public class UpdateRequestBuilder<T extends BaseEntity> {

    public List<String> getUpdateStatements(T oldObject, T newObject) throws IllegalAccessException {
        List<Attr> attrs = Processor.getAttributes(oldObject.getClass());
        List<String> statements = new ArrayList<>();

        for (Attr attr : attrs) {
            attr.field.setAccessible(true);

            Object oldValue = attr.field.get(oldObject);
            Object newValue = attr.field.get(newObject);

            if (!isObjectsEquals(oldValue, newValue)) {
                switch (attr.valueType) {
                    case BASE_VALUE:
                        statements.add(MessageFormat.format(PreparedRequests.UPDATE_OBJECTS.getRequest(),
                                attr.valueType.getValueType(),
                                newValue,
                                oldObject.getId()));
                        continue;
                    case REF_VALUE:
                        if (oldValue == null) {
                            statements.add(MessageFormat.format(PreparedRequests.INSERT_REFERENCES.getRequest(),
                                    attr.id,
                                    ((BaseEntity) newValue).getId(),
                                    oldObject.getId()));
                        } else {
                            statements.add(MessageFormat.format(PreparedRequests.UPDATE_REFERENCES.getRequest(),
                                    attr.valueType.getValueType(),
                                    ((BaseEntity) newValue).getId(),
                                    attr.id,
                                    oldObject.getId()));
                        }
                        continue;
                    case LIST_VALUE:
                        statements.add(MessageFormat.format(PreparedRequests.UPDATE_ATTRIBUTES.getRequest(),
                                attr.valueType.getValueType(),
                                ListValues.getListValueIdByValue(newValue.toString()),
                                attr.id,
                                oldObject.getId()));
                        continue;
                    case DATE_VALUE:
                        statements.add(MessageFormat.format(PreparedRequests.UPDATE_ATTRIBUTES.getRequest(),
                                attr.valueType.getValueType(),
                                BigInteger.valueOf(((Date) newValue).getTime()).toString(),
                                attr.id,
                                oldObject.getId()));
                        continue;
                    case VALUE:
                        statements.add(MessageFormat.format(PreparedRequests.UPDATE_ATTRIBUTES.getRequest(),
                                attr.valueType.getValueType(),
                                newValue == null ? null : "'" + newValue + "'",
                                attr.id,
                                oldObject.getId()));
                        continue;
                }
            }

        }

        return statements;
    }

    private boolean isObjectsEquals(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 == null && obj2 != null) {
            return false;
        }
        if (obj2 == null && obj1 != null) {
            return false;
        }
        if (obj1 instanceof BaseEntity) {
            return ((BaseEntity) obj1).getId().equals(((BaseEntity) obj2).getId());
        }
        return obj1.equals(obj2);
    }

}
