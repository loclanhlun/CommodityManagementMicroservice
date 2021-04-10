package com.commoditymanagement.core.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "commodity")
public class Commodity {

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

    @ManyToOne
    @JoinColumn(name = "categoryid")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Category category;

    @OneToMany(mappedBy = "commodity")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ExportBillDetail> exportBillDetails;

    @OneToMany(mappedBy = "commodities")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ImportBillDetail> importBillDetails;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<ExportBillDetail> getExportBillDetails() {
        return exportBillDetails;
    }

    public void setExportBillDetails(Set<ExportBillDetail> exportBillDetails) {
        this.exportBillDetails = exportBillDetails;
    }

    public Set<ImportBillDetail> getImportBillDetails() {
        return importBillDetails;
    }

    public void setImportBillDetails(Set<ImportBillDetail> importBillDetails) {
        this.importBillDetails = importBillDetails;
    }
}
