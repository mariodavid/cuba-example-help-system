package com.company.cehs.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "CEHS_CUSTOMER")
@Entity(name = "cehs$Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = -8859836866818799762L;

    @Column(name = "NAME")
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}