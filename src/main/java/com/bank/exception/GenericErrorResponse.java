package com.bank.exception;

public class GenericErrorResponse {
 
    private int statusCode;
    private String message;
 
    public GenericErrorResponse(String message)
    {
        super();
        this.message = message;
    }

	public GenericErrorResponse(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public GenericErrorResponse() {
		super();
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
   
	
    
}