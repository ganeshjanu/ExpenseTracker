package com.friends.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.channel.BootstrapHandlers;
import reactor.netty.http.client.HttpClient;

@Configuration
public class AppConfig {
	
	@Bean
	public WebClient getWebClient() {
		HttpClient httpClient = HttpClient.create()
			    .tcpConfiguration(tcpClient ->
			        tcpClient.bootstrap(bootstrap ->
			            BootstrapHandlers.updateLogSupport(bootstrap, new HttpLoggingHandler())));

		return WebClient
			    .builder()
			    .clientConnector(new ReactorClientHttpConnector(httpClient))
			    .build();
		
	}

}
