package com.commoditymanagement.core.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "importbill")
public class ImportBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    @ManyToOne
    @JoinColumn(name = "supplierid")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "warehouseid")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Warehouse warehouses;

    @Column(name = "importdate")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date importDate;

    @OneToMany(mappedBy = "importBill")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ImportBillDetail> importBillDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Warehouse getWarehouse() {
        return warehouses;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouses = warehouse;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Warehouse getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Warehouse warehouses) {
        this.warehouses = warehouses;
    }

    public Set<ImportBillDetail> getImportBillDetails() {
        return importBillDetails;
    }

    public void setImportBillDetails(Set<ImportBillDetail> importBillDetails) {
        this.importBillDetails = importBillDetails;
    }
}
