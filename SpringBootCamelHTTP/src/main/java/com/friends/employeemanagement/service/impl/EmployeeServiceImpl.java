package com.friends.employeemanagement.service.impl;

import org.apache.camel.Produce;
import org.springframework.stereotype.Service;

import com.friends.employeemanagement.beans.EmployeeBean;
import com.friends.employeemanagement.beans.ResponseBean;
import com.friends.employeemanagement.camel.proxy.EmployeeRouteProxy;
import com.friends.employeemanagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Produce(uri = "direct::employeeTxn")
	private EmployeeRouteProxy employeeRouteProxy;
	
	@Override
	public ResponseBean addEmployee(EmployeeBean employeeBean) {
		return employeeRouteProxy.employeeRoute(employeeBean.getAge(), employeeBean);
	}

}
