package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.db.annotations.Attr;
import com.diploma.edu.source.db.annotations.Processor;
import com.diploma.edu.source.model.BaseEntity;
import com.diploma.edu.source.model.ListValues;
import com.diploma.edu.source.servicies.requestBuilder.preparedRequests.InsertPreparedRequests;
import com.diploma.edu.source.servicies.requestBuilder.preparedRequests.UpdatePreparedRequests;

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
            attr.getField().setAccessible(true);

            Object oldValue = attr.getField().get(oldObject);
            Object newValue = attr.getField().get(newObject);

            if (!isObjectsEquals(oldValue, newValue)) {
                switch (attr.getValueType()) {
                    case BASE_VALUE:
                        statements.add(MessageFormat.format(UpdatePreparedRequests.UPDATE_OBJECTS.getRequest(),
                                attr.getField().getName(),
                                newValue instanceof String ? "'" + newValue + "'" : newValue,
                                oldObject.getId()));
                        continue;
                    case REF_VALUE:
                        if (oldValue == null) {
                            statements.add(MessageFormat.format(InsertPreparedRequests.INSERT_REFERENCES.getRequest(),
                                    attr.getId(),
                                    ((BaseEntity) newValue).getId(),
                                    oldObject.getId()));
                        } else {
                            statements.add(MessageFormat.format(UpdatePreparedRequests.UPDATE_REFERENCES.getRequest(),
                                    attr.getValueType().getValueType(),
                                    newValue == null ? null : ((BaseEntity) newValue).getId(),
                                    attr.getId(),
                                    oldObject.getId()));
                        }
                        continue;
                    case LIST_VALUE:
                        if (oldValue == null) {
                            statements.add(MessageFormat.format(InsertPreparedRequests.INSERT_ATTRIBUTES.getRequest(),
                                    attr.getId(),
                                    oldObject.getId(),
                                    null,
                                    null,
                                    ListValues.getListValueIdByValue(newValue == null ? null : newValue.toString())));
                        } else {
                            statements.add(MessageFormat.format(UpdatePreparedRequests.UPDATE_ATTRIBUTES.getRequest(),
                                    attr.getValueType().getValueType(),
                                    ListValues.getListValueIdByValue(newValue == null ? null : newValue.toString()),
                                    attr.getId(),
                                    oldObject.getId()));
                        }
                        continue;
                    case DATE_VALUE:
                        if (oldValue == null) {
                            statements.add(MessageFormat.format(InsertPreparedRequests.INSERT_ATTRIBUTES.getRequest(),
                                    attr.getId(),
                                    oldObject.getId(),
                                    null,
                                    BigInteger.valueOf(((Date) newValue).getTime()).toString(),
                                    null));
                        } else {
                            statements.add(MessageFormat.format(UpdatePreparedRequests.UPDATE_ATTRIBUTES.getRequest(),
                                    attr.getValueType().getValueType(),
                                    BigInteger.valueOf(((Date) newValue).getTime()).toString(),
                                    attr.getId(),
                                    oldObject.getId()));
                        }
                        continue;
                    case VALUE:
                        if (oldValue == null) {
                            statements.add(MessageFormat.format(InsertPreparedRequests.INSERT_ATTRIBUTES.getRequest(),
                                    attr.getId(),
                                    oldObject.getId(),
                                    newValue.toString(),
                                    null,
                                    null));
                        } else {
                            statements.add(MessageFormat.format(UpdatePreparedRequests.UPDATE_ATTRIBUTES.getRequest(),
                                    attr.getValueType().getValueType(),
                                    newValue == null ? null : "'" + newValue + "'",
                                    attr.getId(),
                                    oldObject.getId()));
                        }
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
