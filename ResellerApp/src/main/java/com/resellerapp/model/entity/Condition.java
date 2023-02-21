package com.resellerapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private ConditionType name;

    @Column(nullable = false)
    private String description;

    public Condition() {
    }

    public ConditionType getName() {
        return name;
    }

    public Condition setName(ConditionType name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Condition setDescription(String description) {
        this.description = description;
        return this;
    }
}
