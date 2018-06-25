package com.ruifucredit.cloud.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CenterStarter {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(CenterStarter.class, args);
	}
	
}
