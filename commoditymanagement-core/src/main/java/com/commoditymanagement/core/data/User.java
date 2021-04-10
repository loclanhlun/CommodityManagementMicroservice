package com.commoditymanagement.core.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "fullname")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fullName;

    @Column(name = "email")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @Column(name = "password")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    @Column(name = "gender")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int gender;

    @Column(name = "phonenumber")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phoneNumber;

    @Column(name = "address")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;

    @Column(name = "status")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int status;

    @ManyToOne
    @JoinColumn(name = "roleid")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ExportBill> exportBills;


    @OneToMany(mappedBy = "supplier")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ImportBill> importBills;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
