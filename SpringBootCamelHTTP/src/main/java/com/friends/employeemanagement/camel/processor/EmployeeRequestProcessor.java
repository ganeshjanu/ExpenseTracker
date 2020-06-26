package com.friends.employeemanagement.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.friends.employeemanagement.beans.EmployeeBean;

@Component
public class EmployeeRequestProcessor implements Processor{
	
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void process(Exchange exchange) throws Exception {
		EmployeeBean employeeBean = (EmployeeBean)exchange.getProperty("employee");
		exchange.getIn().setHeader("Content-Type", "application/json");
		exchange.getIn().setBody(mapper.writeValueAsString(employeeBean));
	}


}
