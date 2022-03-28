package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;

import java.util.Date;

@ObjectType(id = 12)
public class Utility extends BaseEntity{

    @Attribute(id = 37, valueType = ValueType.VALUE)
    private String bankBook;

    @Attribute(id = 38, valueType = ValueType.DATE_VALUE)
    private Date dateAndTime;

    @Attribute(id = 47 ,valueType = ValueType.VALUE)
    private Integer startMonthReading;

    @Attribute(id = 39, valueType = ValueType.VALUE)
    private Integer endMonthReading;

    @Attribute(id = 40, valueType = ValueType.VALUE)
    private Float amountToPay;

    @Attribute(id = 41, valueType = ValueType.VALUE)
    private Boolean status;

    @Attribute(id = 42, valueType = ValueType.VALUE)
    private String photoURL;

    @Attribute(id = 43, clazz = Service.class)
    private Service service;

    @Attribute(id = 18, clazz = Address.class)
    private Address address;

    public String getBankBook() {
        return bankBook;
    }

    public void setBankBook(String bankBook) {
        this.bankBook = bankBook;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Integer getEndMonthReading() {
        return endMonthReading;
    }

    public void setEndMonthReading(Integer endMonthReading) {
        this.endMonthReading = endMonthReading;
    }

    public Float getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Float amountToPay) {
        this.amountToPay = amountToPay;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Integer getStartMonthReading() {
        return startMonthReading;
    }

    public void setStartMonthReading(Integer startMonthReading) {
        this.startMonthReading = startMonthReading;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
