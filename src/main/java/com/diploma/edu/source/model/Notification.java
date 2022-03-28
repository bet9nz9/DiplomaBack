package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;
import java.util.Date;


@ObjectType(id = 11)
public class Notification extends BaseEntity {

    @Attribute(id = 32, valueType = ValueType.VALUE)
    private String text;

    @Attribute(id = 33, valueType = ValueType.DATE_VALUE)
    private Date dateAndTime;

    @Attribute(id = 34, valueType = ValueType.VALUE)
    private String title;

    @Attribute(id = 35, clazz = Category.class)
    private Category category;

    @Attribute(id = 36, clazz = User.class)
    private User createdBy;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
