package com.company.zhilv.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "ZHILV_ORDER")
@Entity(name = "zhilv_Order")
public class Order extends StandardEntity {
    private static final long serialVersionUID = 299160535630513088L;

    @Column(name = "CODE")
    protected String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID")
    protected Contract contract;

    @NumberFormat(pattern = "#,###.00")
    @Column(name = "TOTAL_PRICE")
    protected BigDecimal totalPrice;

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

    @NumberFormat(pattern = "#,##0.####")
    @Column(name = "ACTUAL_AMOUNT", precision = 19, scale = 4)
    protected BigDecimal actualAmount = BigDecimal.ZERO;

    @Column(name = "RETURN_TYPE")
    protected String returnType;

    @Temporal(TemporalType.DATE)
    @Column(name = "RETURN_DATE")
    protected Date returnDate;

    @Lob
    @Column(name = "MEMO")
    protected String memo;

    @OrderBy("createTs")
    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<OrderItem> orderItems;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

}