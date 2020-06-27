package com.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * A Simple Spring Cloud Consul app
 *
 * @author Odilio Noronha Filho
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StarterDiscovery {
	  
	public static void main(String[] args) {
		SpringApplication.run(StarterDiscovery.class, args);
	}

}
