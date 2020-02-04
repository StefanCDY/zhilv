package com.company.zhilv.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "ZHILV_CONTRACT")
@Entity(name = "zhilv_Contract")
public class Contract extends StandardEntity {
    private static final long serialVersionUID = -8745019910083733711L;

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
    @JoinColumn(name = "CONTRACT_ID")
    protected FileDescriptor contract;

    @OrderBy("createTs asc")
    @OneToMany(mappedBy = "contract")
    protected List<ContractItem> contractItems;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "contract")
    protected List<ContractFile> files;

    @Lob
    @Column(name = "MEMO")
    protected String memo;

    public List<ContractItem> getContractItems() {
        return contractItems;
    }

    public void setContractItems(List<ContractItem> contractItems) {
        this.contractItems = contractItems;
    }

    public FileDescriptor getContract() {
        return contract;
    }

    public void setContract(FileDescriptor contract) {
        this.contract = contract;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setStatus(ContractStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public ContractStatus getStatus() {
        return status == null ? null : ContractStatus.fromId(status);
    }

    public List<ContractFile> getFiles() {
        return files;
    }

    public void setFiles(List<ContractFile> files) {
        this.files = files;
    }

    public void setCode(String code) {
        this.code = code;
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
}