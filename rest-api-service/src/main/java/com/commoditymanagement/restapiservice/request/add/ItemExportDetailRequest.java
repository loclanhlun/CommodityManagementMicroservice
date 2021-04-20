package com.commoditymanagement.restapiservice.request.add;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class ItemExportDetailRequest {

    @NotBlank(message = "Please enter commodity!")
    @JsonProperty
    private String commodityCode;

    @JsonProperty
    private int quantity;

    @JsonProperty
    private BigDecimal price;

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
