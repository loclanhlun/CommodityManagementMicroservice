package com.commoditymanagement.userservice.request.get;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchExportBillByDateRequest {

    @JsonProperty
    private String fromDate;

    @JsonProperty
    private String toDate;

    @JsonProperty
    private String agencyCode;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }
}
