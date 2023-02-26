package com.resellerapp.model.dto;

import com.resellerapp.model.entity.Offer;

import java.math.BigDecimal;

public class UserWithOfferDTO {
    private Long id;
    private String condition;
    private BigDecimal price;
    private String description;

    public UserWithOfferDTO(Offer offer) {
        this.id = offer.getId();
        this.condition = offer.getCondition().getName().toString();
        this.price = offer.getPrice();
        this.description = offer.getDescription();
    }

    public Long getId() {
        return id;
    }

    public String getCondition() {
        return condition;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
