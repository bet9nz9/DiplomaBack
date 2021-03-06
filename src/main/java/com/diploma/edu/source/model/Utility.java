package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

@ObjectType(id = 12)
public class Utility extends BaseEntity {

    @Attribute(id = 38, valueType = ValueType.DATE_VALUE)
    private Date dateAndTime;

    @Attribute(id = 47, valueType = ValueType.VALUE)
    private BigInteger startMonthReading;

    @Attribute(id = 39, valueType = ValueType.VALUE)
    private BigInteger endMonthReading;

    @Attribute(id = 40, valueType = ValueType.VALUE)
    private BigDecimal amountToPay;

    @Attribute(id = 41, valueType = ValueType.LIST_VALUE)
    private Boolean status;

    @Attribute(id = 42, valueType = ValueType.VALUE)
    private String photoURL;

    @Attribute(id = 43, clazz = Service.class)
    private Service service;

    @Attribute(id = 18, clazz = Address.class)
    private Address address;

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public BigInteger getEndMonthReading() {
        return endMonthReading;
    }

    public void setEndMonthReading(BigInteger endMonthReading) {
        this.endMonthReading = endMonthReading;
    }

    public BigDecimal getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(BigDecimal amountToPay) {
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

    public BigInteger getStartMonthReading() {
        return startMonthReading;
    }

    public void setStartMonthReading(BigInteger startMonthReading) {
        this.startMonthReading = startMonthReading;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Utility copy() {
        Utility newUtility = new Utility();

        newUtility.setId(this.getId());
        newUtility.setDescription(this.getDescription());
        newUtility.setDateAndTime(monthIncrement(this.getDateAndTime()));
        newUtility.setStartMonthReading(this.getStartMonthReading());
        newUtility.setEndMonthReading(this.getEndMonthReading());
        newUtility.setAmountToPay(new BigDecimal(0L));
        newUtility.setStatus(false);
        newUtility.setPhotoURL(null);
        newUtility.setService(this.getService());
        newUtility.setAddress(this.getAddress());

        return newUtility;
    }

    private Date monthIncrement(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }
}
