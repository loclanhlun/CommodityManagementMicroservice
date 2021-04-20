package com.commoditymanagement.userservice.request.add;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class ItemImportBillDetailRequest {

    @JsonProperty
    private String commodityCode;

    @JsonProperty
    private BigDecimal price;

    @JsonProperty
    private int quantity;

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
