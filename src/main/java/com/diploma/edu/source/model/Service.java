package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;

import java.math.BigDecimal;

@ObjectType(id = 14)
public class Service extends BaseEntity {

    @Attribute(id = 37, valueType = ValueType.VALUE)
    private String bankBook;

    @Attribute(id = 45, valueType = ValueType.VALUE)
    private String title;

    @Attribute(id = 46, valueType = ValueType.VALUE)
    private BigDecimal tariff;

    @Attribute(id = 52, clazz = ServiceType.class)
    private ServiceType serviceType;

    @Attribute(id = 53, clazz = Address.class)
    private Address address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getTariff() {
        return tariff;
    }

    public void setTariff(BigDecimal tariff) {
        this.tariff = tariff;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getBankBook() {
        return bankBook;
    }

    public void setBankBook(String bankBook) {
        this.bankBook = bankBook;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
