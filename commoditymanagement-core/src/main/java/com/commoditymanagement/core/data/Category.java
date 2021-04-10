package com.commoditymanagement.core.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

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


    @OneToMany(mappedBy = "category")
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Commodity> commodity;

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

    public Set<Commodity> getCommodity() {
        return commodity;
    }

    public void setCommodity(Set<Commodity> commodity) {
        this.commodity = commodity;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
    
    
}
