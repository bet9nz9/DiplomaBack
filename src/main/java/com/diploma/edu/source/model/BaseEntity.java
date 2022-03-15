package com.diploma.edu.source.model;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
    protected Long id;
    protected String name;
    protected String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
