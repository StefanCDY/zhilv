package com.company.zhilv.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum AgreementStatus implements EnumClass<Integer> {

    RUNNING(10),
    CANCELED(20),
    EXPIRED(30),
    UNENFORCED(40);

    private Integer id;

    AgreementStatus(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static AgreementStatus fromId(Integer id) {
        for (AgreementStatus at : AgreementStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}