package com.company.zhilv.entity;

import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "ZHILV_ORDER")
@Entity(name = "zhilv_Order")
public class Order extends StandardEntity {
    private static final long serialVersionUID = 299160535630513088L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    protected Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    protected Product product;

    @NumberFormat(pattern = "#,##0.####")
    @Column(name = "AMOUNT")
    protected BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEASURE_UNIT_ID")
    protected MeasureUnit measureUnit;

    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "UNIT_PRICE")
    protected BigDecimal unitPrice;

    @Lob
    @Column(name = "REQUIREMENT")
    protected String requirement;

    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_DATE")
    protected Date orderDate;

    @Column(name = "INVOICE_TYPE")
    protected Integer invoiceType;

    @Column(name = "DELIVERY_TYPE")
    protected Integer deliveryType;

    @Lob
    @Column(name = "DELIVERY_ADDRESS")
    protected String deliveryAddress;

    @Column(name = "RECEIVER")
    protected String receiver;

    @Column(name = "RECEIVE_PHONE")
    protected String receivePhone;

    @Lob
    @Column(name = "RECEIVE_ADDRESS")
    protected String receiveAddress;

    @Column(name = "INVOICE_NUMBER")
    protected String invoiceNumber;

    @Lob
    @Column(name = "INVOICE_ADDRESS")
    protected String invoiceAddress;

    @Column(name = "INVOICE_CONTACT")
    protected String invoiceContact;

    @Column(name = "INVOICE_PHONE")
    protected String invoicePhone;

    @Column(name = "IS_DELIVER")
    protected Boolean isDeliver;

    @Column(name = "LOGISTICS_NUMBER")
    protected String logisticsNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "DELIVERY_TIME")
    protected Date deliveryTime;

    @Column(name = "CARGO_OUT_NUMBER")
    protected String cargoOutNumber;

    @Lob
    @Column(name = "MEMO")
    protected String memo;

    public String getCargoOutNumber() {
        return cargoOutNumber;
    }

    public void setCargoOutNumber(String cargoOutNumber) {
        this.cargoOutNumber = cargoOutNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Boolean getIsDeliver() {
        return isDeliver;
    }

    public void setIsDeliver(Boolean isDeliver) {
        this.isDeliver = isDeliver;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getInvoicePhone() {
        return invoicePhone;
    }

    public void setInvoicePhone(String invoicePhone) {
        this.invoicePhone = invoicePhone;
    }

    public String getInvoiceContact() {
        return invoiceContact;
    }

    public void setInvoiceContact(String invoiceContact) {
        this.invoiceContact = invoiceContact;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType == null ? null : DeliveryType.fromId(deliveryType);
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType == null ? null : deliveryType.getId();
    }

    public InvoiceType getInvoiceType() {
        return invoiceType == null ? null : InvoiceType.fromId(invoiceType);
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.getId();
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}