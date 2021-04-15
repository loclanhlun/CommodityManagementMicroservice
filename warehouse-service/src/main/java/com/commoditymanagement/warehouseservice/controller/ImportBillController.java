package com.commoditymanagement.warehouseservice.controller;

import com.commoditymanagement.warehouseservice.request.add.AddImportBillRequest;
import com.commoditymanagement.warehouseservice.request.edit.EditImportBillRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/v1/import-bill")
public class ImportBillController {

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListImportBill(){


        return ResponseEntity.ok("1");
    }

    @PostMapping(value = "/add-import-bill")
    public ResponseEntity<?> addImportBill(@RequestBody AddImportBillRequest request){

        //TODO: add import bill

        //TODO: get import bill id and insert into ImportBillDetail

        //TODO: Insert into CommodityWarehouse
        return ResponseEntity.ok("1");
    }


    @GetMapping(value = "/import-bill-details/{id}")
    public ResponseEntity<?> getImportBillDetailByImportBillId(@PathVariable("id") Long importBillId){

        return ResponseEntity.ok("a");
    }


}
