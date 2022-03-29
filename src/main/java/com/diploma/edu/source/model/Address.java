package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;

import java.math.BigInteger;

@ObjectType(id = 7)
public class Address extends BaseEntity {

    @Attribute(id = 17, valueType = ValueType.VALUE)
    private BigInteger flat;

    @Attribute(id = 49, valueType = ValueType.VALUE)
    private BigInteger apartmentNumber;

    @Attribute(id = 16, clazz = Building.class)
    private Building building;

    @Attribute(id = 48, clazz = User.class)
    private User referencedUser;

    public BigInteger getFlat() {
        return flat;
    }

    public void setFlat(BigInteger flat) {
        this.flat = flat;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public User getReferencedUser() {
        return referencedUser;
    }

    public void setReferencedUser(User referencedUser) {
        this.referencedUser = referencedUser;
    }

    public BigInteger getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(BigInteger apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
