package com.commoditymanagement.core.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "commoditywarehouse")
public class CommodityWarehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commodityId")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Commodity commodity;

    @ManyToOne
    @JoinColumn(name = "warehouseId")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Warehouse warehouse;

    @Column(name = "quantity")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
