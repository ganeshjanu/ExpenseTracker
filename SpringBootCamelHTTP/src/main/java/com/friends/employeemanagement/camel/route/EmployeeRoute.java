package com.friends.employeemanagement.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.friends.employeemanagement.camel.processor.EmployeeRequestProcessor;
import com.friends.employeemanagement.camel.processor.EmployeeResponseProcessor;

@Component
public class EmployeeRoute extends RouteBuilder {
	
	@Autowired
	private EmployeeRequestProcessor employeeRequestProcessor;
	
	@Autowired
	private EmployeeResponseProcessor emmployeeResponseProcessor;

	@Override
	public void configure() throws Exception {
		
		onException(Exception.class)
			.handled(true)
			.process(new Processor() {
				@Override
				public void process(Exchange exchange) throws Exception {
					exchange.getException().printStackTrace();
				}
			});
	
		from("direct::employeeTxn")
		.routeId("employeeroute")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.process(employeeRequestProcessor)
		.toD("http://dummy.restapiexample.com/api/v1/create")
		.process(emmployeeResponseProcessor);
		
	}
	
	

}
