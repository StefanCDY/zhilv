package com.company.zhilv.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "ZHILV_AGREEMENT")
@Entity(name = "zhilv_Agreement")
public class Agreement extends StandardEntity {
    private static final long serialVersionUID = -7245923018850142184L;

    @Column(name = "CODE")
    protected String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    protected Customer customer;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    protected Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    protected Date endDate;

    @Column(name = "STATUS")
    protected Integer status;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGREEMENT_ID")
    protected FileDescriptor agreement;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "agreement")
    protected List<AgreementFile> files;

    @Column(name = "MEMO")
    protected String memo;

    public FileDescriptor getAgreement() {
        return agreement;
    }

    public void setAgreement(FileDescriptor agreement) {
        this.agreement = agreement;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setStatus(AgreementStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public AgreementStatus getStatus() {
        return status == null ? null : AgreementStatus.fromId(status);
    }

    public List<AgreementFile> getFiles() {
        return files;
    }

    public void setFiles(List<AgreementFile> files) {
        this.files = files;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}