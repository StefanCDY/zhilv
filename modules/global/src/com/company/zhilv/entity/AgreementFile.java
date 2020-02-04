package com.company.zhilv.entity;

import com.haulmont.cuba.core.entity.FileDescriptor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "zhilv_AgreementFile")
public class AgreementFile extends FileDescriptor {
    private static final long serialVersionUID = 5806198920897353938L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGREEMENT_ID")
    protected Agreement agreement;

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }
}