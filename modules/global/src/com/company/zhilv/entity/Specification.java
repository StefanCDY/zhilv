package com.company.zhilv.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@NamePattern("%s|name")
@Table(name = "ZHILV_SPECIFICATION")
@Entity(name = "zhilv_Specification")
public class Specification extends StandardEntity {
    private static final long serialVersionUID = -2990312472648817557L;

    @Column(name = "NAME")
    protected String name;

    @Lob
    @Column(name = "MEMO")
    protected String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}