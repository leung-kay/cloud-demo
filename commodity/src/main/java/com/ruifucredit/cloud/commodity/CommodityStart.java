package com.ruifucredit.cloud.commodity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.ruifucredit.cloud")
public class CommodityStart {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CommodityStart.class, args);
    }

}
