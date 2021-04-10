package com.commoditymanagement.core.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "exportbilldetail")
public class ExportBillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exportbillid")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ExportBill exportBill;

    @ManyToOne
    @JoinColumn(name = "commodityid")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Commodity commodity;

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

    public ExportBill getExportBill() {
        return exportBill;
    }

    public void setExportBill(ExportBill exportBill) {
        this.exportBill = exportBill;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
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
