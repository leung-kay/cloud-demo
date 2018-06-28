package com.ruifucredit.cloud.watcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
@EnableDiscoveryClient
public class WatcherStarter {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(WatcherStarter.class, args);
	}

}
