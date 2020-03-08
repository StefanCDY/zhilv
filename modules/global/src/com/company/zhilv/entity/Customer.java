package com.company.zhilv.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.core.global.DesignSupport;

import javax.persistence.*;

@DesignSupport("{'generateDdl':false}")
@NamePattern("%s|name")
@Table(name = "ZHILV_CUSTOMER")
@Entity(name = "zhilv_Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = -6655291878397203008L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "ADDRESS")
    protected String address;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LICENCE_ID")
    protected FileDescriptor licence;

    @Column(name = "CREDIT_CODE")
    protected String creditCode;

    @Column(name = "BANK_NAME")
    protected String bankName;

    @Column(name = "BANK_ACCOUNT")
    protected String bankAccount;

    @Column(name = "SALE_AREA")
    protected String saleArea;

    @Column(name = "CONTACT")
    protected String contact;

    @Column(name = "INVOICE_CONTACT")
    protected String invoiceContact;

    @Column(name = "INVOICE_PHONE")
    protected String invoicePhone;

    @Lob
    @Column(name = "INVOICE_ADDRESS")
    protected String invoiceAddress;

    @Column(name = "RECEIVER")
    protected String receiver;

    @Column(name = "RECEIVE_PHONE")
    protected String receivePhone;

    @Lob
    @Column(name = "RECEIVE_ADDRESS")
    protected String receiveAddress;

    @Lob
    @Column(name = "MEMO")
    protected String memo;

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

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getSaleArea() {
        return saleArea;
    }

    public void setSaleArea(String saleArea) {
        this.saleArea = saleArea;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLicence(FileDescriptor licence) {
        this.licence = licence;
    }

    public FileDescriptor getLicence() {
        return licence;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}