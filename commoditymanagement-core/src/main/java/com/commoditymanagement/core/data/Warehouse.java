package com.commoditymanagement.core.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "code")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;

    @Column(name = "name")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @Column(name = "status")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int status;

    @OneToMany(mappedBy = "warehouse")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ExportBill> exportBills;

    @OneToMany(mappedBy = "warehouses")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ImportBill> importBills;

    @OneToMany(mappedBy = "warehouse")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CommodityWarehouse> commodityWarehouses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<ExportBill> getExportBills() {
        return exportBills;
    }

    public void setExportBills(Set<ExportBill> exportBills) {
        this.exportBills = exportBills;
    }

    public Set<ImportBill> getImportBills() {
        return importBills;
    }

    public void setImportBills(Set<ImportBill> importBills) {
        this.importBills = importBills;
    }

    public Set<CommodityWarehouse> getCommodityWarehouses() {
        return commodityWarehouses;
    }

    public void setCommodityWarehouses(Set<CommodityWarehouse> commodityWarehouses) {
        this.commodityWarehouses = commodityWarehouses;
    }
}
