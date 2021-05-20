package com.commoditymanagement.core.constant;

public class UrlConstants {
    // URL of Agency
    public static final String GET_AGENCY_LIST_URL = "http://user-service/rest/v1/admin/agency/list";
    public static final String SEARCH_AGENCY = "http://user-service/rest/v1/admin/agency/search";
    public static final String GET_AGENCY_BY_ID_URL = "http://user-service/rest/v1/admin/agency/{id}";
    public static final String ADD_AGENCY_URL = "http://user-service/rest/v1/admin/agency/add-agency";
    public static final String EDIT_AGENCY_URL = "http://user-service/rest/v1/admin/agency/edit-agency";
    public static final String REMOVE_AGENCY_BY_ID_URL = "http://user-service/rest/v1/admin/agency/remove-agency/{id}";

    //URL of Category
    public static final String GET_CATEGORY_LIST_URL = "http://user-service/rest/v1/admin/category/list";
    public static final String SEARCH_CATEGORY = "http://user-service/rest/v1/admin/category/search";
    public static final String GET_CATEGORY_BY_ID_URL = "http://user-service/rest/v1/admin/category/{id}";
    public static final String ADD_CATEGORY_URL = "http://user-service/rest/v1/admin/category/add-category";
    public static final String EDIT_CATEGORY_URL = "http://user-service/rest/v1/admin/category/edit-category";
    public static final String REMOVE_CATEGORY_BY_ID_URL = "http://user-service/rest/v1/admin/category/remove-category/{id}";

    //URL of Commodity
    public static final String GET_COMMODITY_LIST_URL = "http://user-service/rest/v1/admin/commodity/list";
    public static final String SEARCH_COMMODITY = "http://user-service/rest/v1/admin/commodity/search";
    public static final String GET_COMMODITY_LIST_BY_CATEGORY_ID_URL = "http://user-service/rest/v1/admin/commodity/list-commodity";
    public static final String GET_COMMODITY_BY_ID_URL = "http://user-service/rest/v1/admin/commodity/{id}";
    public static final String ADD_COMMODITY_URL = "http://user-service/rest/v1/admin/commodity/add-commodity";
    public static final String EDIT_COMMODITY_URL = "http://user-service/rest/v1/admin/commodity/edit-commodity";
    public static final String REMOVE_COMMODITY_BY_ID_URL = "http://user-service/rest/v1/admin/commodity/remove-commodity/{id}";

    //URL of Supplier
    public static final String GET_SUPPLIER_LIST_URL = "http://user-service/rest/v1/admin/supplier/list";
    public static final String SEARCH_SUPPLIER = "http://user-service/rest/v1/admin/supplier/search";
    public static final String GET_SUPPLIER_BY_ID_URL = "http://user-service/rest/v1/admin/supplier/{id}";
    public static final String ADD_SUPPLIER_URL = "http://user-service/rest/v1/admin/supplier/add-supplier";
    public static final String EDIT_SUPPLIER_URL = "http://user-service/rest/v1/admin/supplier/edit-supplier";
    public static final String REMOVE_SUPPLIER_BY_ID_URL = "http://user-service/rest/v1/admin/supplier/remove-supplier/{id}";

    //URL of Warehouse
    public static final String GET_WAREHOUSE_LIST_URL = "http://user-service/rest/v1/admin/warehouse/list";
    public static final String SEARCH_WAREHOUSE = "http://user-service/rest/v1/admin/warehouse/search";
    public static final String GET_WAREHOUSE_BY_ID_URL = "http://user-service/rest/v1/admin/warehouse/{id}";
    public static final String ADD_WAREHOUSE_URL = "http://user-service/rest/v1/admin/warehouse/add-warehouse";
    public static final String EDIT_WAREHOUSE_URL = "http://user-service/rest/v1/admin/warehouse/edit-warehouse";
    public static final String REMOVE_WAREHOUSE_BY_ID_URL = "http://user-service/rest/v1/admin/warehouse/remove-warehouse/{id}";

    //URL of User
    public static final String LOGIN_URL = "http://user-service/rest/v1/login";
    public static final String SEARCH_USER = "http://user-service/rest/v1/admin/user/search";
    public static final String GET_USER_LIST_URL = "http://user-service/rest/v1/admin/user/list";
    public static final String GET_ROLE_LIST_URL = "http://user-service/rest/v1/admin/user/list-role";
    public static final String GET_USER_BY_ID_URL = "http://user-service/rest/v1/admin/user/{id}";
    public static final String ADD_USER_URL = "http://user-service/rest/v1/admin/user/add-user";
    public static final String EDIT_USER_URL = "http://user-service/rest/v1/admin/user/edit-user";
    public static final String REMOVE_USER_BY_ID_URL = "http://user-service/rest/v1/admin/user/remove-user/{id}";

    //URL of ImportBill
    public static final String GET_IMPORT_LIST_URL = "http://user-service/rest/v1/import-bill/list";
    public static final String SEARCH_IMPORT_URL = "http://user-service/rest/v1/import-bill/search";
    public static final String ADD_IMPORT_URL = "http://user-service/rest/v1/import-bill/add-import-bill";
    public static final String DELETE_IMPORT_BY_MAX_ID_URL = "http://user-service/rest/v1/import-bill/delete-import-bill";

    //URL of ImportBillDetail
    public static final String GET_IMPORT_DETAIL_BY_IMPORT_BILL_ID_URL = "http://user-service/rest/v1/import-bill-detail/list/{id}";
    public static final String ADD_IMPORT_DETAIL_URL = "http://user-service/rest/v1/import-bill-detail/add-import-bill-detail";

    //URL of ExportBill
    public static final String GET_EXPORT_LIST_URL = "http://user-service/rest/v1/export-bill/list";
    public static final String SEARCH_EXPORT_URL = "http://user-service/rest/v1/export-bill/search";
    public static final String ADD_EXPORT_URL = "http://user-service/rest/v1/export-bill/add-export-bill";
    public static final String DELETE_EXPORT_BY_MAX_ID_URL = "http://user-service/rest/v1/export-bill/delete-export-bill";

    //URL of ImportBillDetail
    public static final String GET_EXPORT_DETAIL_BY_IMPORT_BILL_ID_URL = "http://user-service/rest/v1/export-bill-detail/list/{id}";
    public static final String ADD_EXPORT_DETAIL_URL = "http://user-service/rest/v1/export-bill-detail/add-export-bill-detail";

    //URL of CommodityWarehouse
    public static final String GET_COMMODITY_WAREHOUSE_LIST_URL = "http://user-service/rest/v1/commodity-warehouse/list";
    public static final String SEARCH_COMMODITY_WAREHOUSE_URL = "http://user-service/rest/v1/commodity-warehouse/search-commodity-warehouse";

}
