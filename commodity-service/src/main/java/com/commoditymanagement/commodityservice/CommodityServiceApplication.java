package com.commoditymanagement.commodityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Entity;

@EntityScan(basePackages = {"com.commoditymanagement.core.data"})
@EnableJpaRepositories(basePackages = {"com.commoditymanagement.commodityservice.repository"} )
@SpringBootApplication
@EnableEurekaClient
public class CommodityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommodityServiceApplication.class, args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
