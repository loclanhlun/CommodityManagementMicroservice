package com.commoditymanagement.core.constant;

import java.util.HashMap;

public class ResponseConstant {
	
	private static HashMap<String, String> resultCode = new HashMap<>();
	
	public final static String RESULT_CODE_SUCCESS = "0";
	
	public final static String RESULT_CODE_ERROR = "999";
	
	public final static String ADMIN_ROLE = "ADMIN";
	
	public final static String STAFF_ROLE = "USER";
	
	static {
		resultCode.put(RESULT_CODE_SUCCESS, "Success");
		resultCode.put(RESULT_CODE_ERROR, "Error");
	}

	public static HashMap<String, String> getResultCode() {
		return resultCode;
	}
	
}
