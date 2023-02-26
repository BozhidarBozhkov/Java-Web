package com.resellerapp.model.dto;

import com.resellerapp.model.entity.Condition;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AddOfferDTO {

    @NotNull
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters")
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private String condition;

    public AddOfferDTO() {}

    public String getDescription() {
        return description;
    }

    public AddOfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public AddOfferDTO setCondition(String condition) {
        this.condition = condition;
        return this;
    }
}
