package com.commoditymanagement.core.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "exportbill")
public class ExportBill {

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
    @JoinColumn(name = "agencyid")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "warehouseid")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Warehouse warehouse;

    @Column(name = "exportdate")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date exportDate;

    @OneToMany(mappedBy = "exportBill")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ExportBillDetail> exportBillDetails;

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

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }

    public Set<ExportBillDetail> getExportBillDetails() {
        return exportBillDetails;
    }

    public void setExportBillDetails(Set<ExportBillDetail> exportBillDetails) {
        this.exportBillDetails = exportBillDetails;
    }
}
