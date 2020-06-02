package com.friends.serveradmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class ServerAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerAdminApplication.class, args);
	}

}
