package com.company.zhilv.entity;

import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "ZHILV_ORDER_ITEM")
@Entity(name = "zhilv_OrderItem")
public class OrderItem extends StandardEntity {
    private static final long serialVersionUID = 1472623833098485670L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    protected Order order;

    @OnDelete(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    protected Product product;

    @Column(name = "BATCH_NUMBER")
    protected String batchNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEASURE_UNIT_ID")
    protected MeasureUnit measureUnit;

    @Column(name = "AMOUNT")
    protected BigDecimal amount;

    @NumberFormat(pattern = "#,###.00")
    @Column(name = "UNIT_PRICE")
    protected BigDecimal unitPrice;

    @NumberFormat(pattern = "#,###.00")
    @Column(name = "TOTAL_PRICE")
    protected BigDecimal totalPrice;

    @Lob
    @Column(name = "MEMO")
    protected String memo;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}