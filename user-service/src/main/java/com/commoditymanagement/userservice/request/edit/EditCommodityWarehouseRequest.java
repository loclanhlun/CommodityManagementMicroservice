package com.commoditymanagement.userservice.request.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class EditCommodityWarehouseRequest {

    private Long id;

    @JsonProperty
    private BigDecimal unitPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
