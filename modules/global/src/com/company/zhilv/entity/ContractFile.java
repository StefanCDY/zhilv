package com.company.zhilv.entity;

import com.haulmont.cuba.core.entity.FileDescriptor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "zhilv_ContractFile")
public class ContractFile extends FileDescriptor {
    private static final long serialVersionUID = -1238449335858981359L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID")
    protected Contract contract;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}