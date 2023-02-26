package com.resellerapp.model.dto;

import com.resellerapp.model.entity.Offer;

import java.math.BigDecimal;

public class OtherUserOfferDto {

    private Long id;
    private String seller;
    private String condition;
    private String description;
    private BigDecimal price;

    public OtherUserOfferDto(Offer offer) {
        this.id = offer.getId();
        this.seller = offer.getOwner().getUsername();
        this.description = offer.getDescription();
        this.condition = offer.getCondition().getName().toString();
        this.price = offer.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getSeller() {
        return seller;
    }

    public String getCondition() {
        return condition;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
