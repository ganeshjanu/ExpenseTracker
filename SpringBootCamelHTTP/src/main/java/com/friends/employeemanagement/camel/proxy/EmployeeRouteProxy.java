package com.friends.employeemanagement.camel.proxy;

import org.apache.camel.ExchangeProperty;

import com.friends.employeemanagement.beans.EmployeeBean;
import com.friends.employeemanagement.beans.ResponseBean;

public interface EmployeeRouteProxy {
	
	public ResponseBean employeeRoute(@ExchangeProperty("empAge") String age, @ExchangeProperty("employee") EmployeeBean employee);

}
