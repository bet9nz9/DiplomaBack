package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;

@ObjectType(id = 11)
public class Contact extends BaseEntity {

    @Attribute(id = 29, valueType = ValueType.VALUE)
    private String value;

    @Attribute(id = 30, clazz = ContactType.class)
    private ContactType contactType;

    @Attribute(id = 31, clazz = User.class)
    private User user;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = user;
    }
}
