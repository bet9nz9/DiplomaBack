package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;

@ObjectType(id = 2)
public class Entrance extends BaseEntity {

    @Attribute(id = 5, clazz = Type.class)
    protected Type entranceType;

    @Attribute(id = 7, clazz = Building.class)
    protected Building building;

    @Attribute(id = 6, valueType = ValueType.LIST_VALUE)
    protected Boolean isOpen;

    @Attribute(id = 50, valueType = ValueType.LIST_VALUE)
    protected Boolean isAvailable;

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Type getEntranceType() {
        return entranceType;
    }

    public void setEntranceType(Type entranceType) {
        this.entranceType = entranceType;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
