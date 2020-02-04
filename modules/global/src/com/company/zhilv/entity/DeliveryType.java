package com.company.zhilv.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum DeliveryType implements EnumClass<Integer> {

    EXPRESS(10),
    PICK(20),
    OTHER(30);

    private Integer id;

    DeliveryType(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static DeliveryType fromId(Integer id) {
        for (DeliveryType at : DeliveryType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}