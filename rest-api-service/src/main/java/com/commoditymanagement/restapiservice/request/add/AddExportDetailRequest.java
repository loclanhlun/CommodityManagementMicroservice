package com.commoditymanagement.restapiservice.request.add;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class AddExportDetailRequest {

    @JsonProperty
    private List<ItemExportDetailRequest> data;

    public List<ItemExportDetailRequest> getData() {
        return data;
    }

    public void setData(List<ItemExportDetailRequest> data) {
        this.data = data;
    }
}
