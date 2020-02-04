package com.company.zhilv.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ContractStatus implements EnumClass<Integer> {

    RUNNING(10),
    CANCELED(20),
    EXPIRED(30),
    UNENFORCED(40);

    private Integer id;

    ContractStatus(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ContractStatus fromId(Integer id) {
        for (ContractStatus at : ContractStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}