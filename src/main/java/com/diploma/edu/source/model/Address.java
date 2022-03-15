package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;

@ObjectType(id = 8)
public class Address extends BaseEntity {

    @Attribute(id = 17, valueType = ValueType.VALUE)
    private String flat;

    @Attribute(id = 49, valueType = ValueType.VALUE)
    private Integer apartmentNumber;

    @Attribute(id = 16, clazz = Building.class)
    private Building building;

    @Attribute(id = 48, clazz = User.class)
    private User user;

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
