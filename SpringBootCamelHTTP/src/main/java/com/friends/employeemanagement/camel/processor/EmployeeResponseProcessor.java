package com.friends.employeemanagement.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.friends.employeemanagement.beans.ResponseBean;

@Component
public class EmployeeResponseProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String response = exchange.getIn().getBody(String.class);
		ObjectMapper mapper = new ObjectMapper();
		ResponseBean responseBean = mapper.readValue(response, ResponseBean.class);
		exchange.getIn().setBody(responseBean);
	}

}
