package com.commoditymanagement.core.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "address")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;

    @Column(name = "phoneNumber")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phoneNumber;

    @Column(name = "status")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int status;

    @OneToMany(mappedBy = "warehouse")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonManagedReference
    private Set<ExportBill> exportBills;

    @OneToMany(mappedBy = "warehouses")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonManagedReference
    private Set<ImportBill> importBills;

    @OneToMany(mappedBy = "warehouse")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonManagedReference
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
