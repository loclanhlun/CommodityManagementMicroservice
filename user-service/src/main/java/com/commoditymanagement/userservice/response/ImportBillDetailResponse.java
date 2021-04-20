package com.commoditymanagement.userservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ImportBillDetailResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String commodityName;

    @JsonProperty
    private Long importBillId;

    @JsonProperty
    private int quantity;

    @JsonProperty
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Long getImportBillId() {
        return importBillId;
    }

    public void setImportBillId(Long importBillId) {
        this.importBillId = importBillId;
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
