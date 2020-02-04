package com.company.zhilv.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum InvoiceType implements EnumClass<Integer> {

    SPECIAL_INVOICE(10),
    PLAIN_INVOICE(20),
    NONE(30);

    private Integer id;

    InvoiceType(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static InvoiceType fromId(Integer id) {
        for (InvoiceType at : InvoiceType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}