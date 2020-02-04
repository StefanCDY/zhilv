package com.company.zhilv.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;

@NamePattern("%s|name")
@Table(name = "ZHILV_CUSTOMER")
@Entity(name = "zhilv_Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = -6655291878397203008L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "CREDIT_CODE")
    protected String creditCode;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LICENCE_ID")
    protected FileDescriptor licence;

    @Lob
    @Column(name = "MEMO")
    protected String memo;

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