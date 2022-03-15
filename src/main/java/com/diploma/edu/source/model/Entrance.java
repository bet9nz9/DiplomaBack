package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;

@ObjectType(id = 2)
public class Entrance extends BaseEntity {

    @Attribute(id = 5, clazz = Type.class)
    protected Type type;

    @Attribute(id = 7, clazz = Building.class)
    protected Building building;

    @Attribute(id = 6, valueType = ValueType.VALUE)
    protected Boolean isActive;

    @Attribute(id = 50, valueType = ValueType.VALUE)
    protected Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
