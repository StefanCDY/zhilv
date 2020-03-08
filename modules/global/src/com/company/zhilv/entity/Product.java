package com.company.zhilv.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@NamePattern("%s %s  %s %s|name,specification,packing,manufactureDate")
@Table(name = "ZHILV_PRODUCT")
@Entity(name = "zhilv_Product")
public class Product extends StandardEntity {
    private static final long serialVersionUID = -5239613863674929825L;

    @Column(name = "NAME")
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPECIFICATION_ID")
    protected Specification specification;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(specification, product.specification) &&
                Objects.equals(packing, product.packing) &&
                Objects.equals(place, product.place) &&
                Objects.equals(manufactureDate, product.manufactureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, specification, packing, place, manufactureDate);
    }
}