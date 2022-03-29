package com.diploma.edu.source.model;

import java.io.Serializable;
import java.math.BigInteger;

public abstract class BaseEntity implements Serializable {
    protected BigInteger id;
    protected String name;
    protected String description;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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
