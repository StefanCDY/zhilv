package com.company.zhilv.entity;

import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "ZHILV_CONTRACT_ITEM")
@Entity(name = "zhilv_ContractItem")
public class ContractItem extends StandardEntity {
    private static final long serialVersionUID = 4602864683328398363L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID")
    protected Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    protected Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEASURE_UNIT_ID")
    protected MeasureUnit measureUnit;

    @Column(name = "BATCH_NUMBER")
    protected String batchNumber;

    @NumberFormat(pattern = "#,##0.####")
    @Column(name = "AMOUNT")
    protected BigDecimal amount;

    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "UNIT_PRICE")
    protected BigDecimal unitPrice;

    @Lob
    @Column(name = "MEMO")
    protected String memo;

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}