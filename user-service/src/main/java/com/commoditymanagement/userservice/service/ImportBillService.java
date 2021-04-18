package com.commoditymanagement.userservice.service;

import com.commoditymanagement.core.data.User;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;

public interface ImportBillService {

    void save(AddImportBillRequest request, User user);

}
