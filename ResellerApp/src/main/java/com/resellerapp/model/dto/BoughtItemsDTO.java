package com.resellerapp.model.dto;

import com.resellerapp.model.entity.Offer;

import java.math.BigDecimal;

public class BoughtItemsDTO {
    private String description;
    private BigDecimal price;

    public BoughtItemsDTO(Offer offer) {
        this.description = offer.getDescription();
        this.price = offer.getPrice();
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
