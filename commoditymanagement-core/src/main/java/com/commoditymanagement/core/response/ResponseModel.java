package com.commoditymanagement.core.response;

import java.util.List;

public class ResponseModel {
	
	private String resultCode;

    private String message;
    
    private Object object;
    
   
    
    

    public ResponseModel() {
    	
    }



	public String getResultCode() {
		return resultCode;
	}



	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public Object getObject() {
		return object;
	}



	public void setObject(Object object) {
		this.object = object;
	}





   
}
