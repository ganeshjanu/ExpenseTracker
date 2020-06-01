package com.friends.employee.beans;

import java.util.List;

public class ErrorResponse 
{
	
	public ErrorResponse() {}
    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }
 
    //General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    private List<String> details;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
    
	@Override
	public String toString() {
		return this.message + "," + this.details;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj instanceof ErrorResponse) {
			ErrorResponse errorResponse = (ErrorResponse) obj;
			return errorResponse.getMessage().equalsIgnoreCase(this.message) && errorResponse.getDetails().equals(details);
		} else {
			return false;
		}
	}
    
	@Override
	public int hashCode() {
		return message.hashCode();
	}

}
