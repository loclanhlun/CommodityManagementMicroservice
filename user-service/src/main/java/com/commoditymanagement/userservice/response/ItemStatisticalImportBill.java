package com.commoditymanagement.userservice.response;

import java.math.BigDecimal;

public class ItemStatisticalImportBill {

    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
