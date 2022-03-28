package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;

@ObjectType(id = 14)
public class Service extends BaseEntity {

    @Attribute(id = 45, valueType = ValueType.VALUE)
    private String title;

    @Attribute(id = 46, valueType = ValueType.VALUE)
    private Float tariff;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getTariff() {
        return tariff;
    }

    public void setTariff(Float tariff) {
        this.tariff = tariff;
    }
}
