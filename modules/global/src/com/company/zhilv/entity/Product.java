package com.company.zhilv.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Date;

@NamePattern("%s %s %s|name,specification,number")
@Table(name = "ZHILV_PRODUCT")
@Entity(name = "zhilv_Product")
public class Product extends StandardEntity {
    private static final long serialVersionUID = -5239613863674929825L;

    @Column(name = "NAME")
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPECIFICATION_ID")
    protected Specification specification;

    @Column(name = "NUMBER_")
    protected String number;

    @Column(name = "PACKING")
    protected String packing;

    @Column(name = "PLACE")
    protected String place;

    @Temporal(TemporalType.DATE)
    @Column(name = "MANUFACTURE_DATE")
    protected Date manufactureDate;

    @Lob
    @Column(name = "MEMO")
    protected String memo;

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public Specification getSpecification() {
        return specification;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}