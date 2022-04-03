package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;

@ObjectType(id = 3)
public class Ekey extends BaseEntity {

    @Attribute(id = 8, valueType = ValueType.VALUE)
    protected String keyCode;

    @Attribute(id = 9, valueType = ValueType.LIST_VALUE)
    protected Boolean isActive;

    @Attribute(id = 10, clazz = User.class)
    protected User referencedUser;

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public User getReferencedUser() {
        return referencedUser;
    }

    public void setReferencedUser(User referencedUser) {
        this.referencedUser = referencedUser;
    }
}
