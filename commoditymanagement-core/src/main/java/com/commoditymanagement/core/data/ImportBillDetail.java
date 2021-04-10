package com.commoditymanagement.core.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "importbilldetail")
public class ImportBillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "importbillid")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ImportBill importBill;

    @ManyToOne
    @JoinColumn(name = "commodityid")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Commodity commodities;

    @Column(name = "quantity")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int quantity;

    @Column(name = "amount")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImportBill getImportBill() {
        return importBill;
    }

    public void setImportBill(ImportBill importBill) {
        this.importBill = importBill;
    }

    public Commodity getCommodities() {
        return commodities;
    }

    public void setCommodities(Commodity commodities) {
        this.commodities = commodities;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
