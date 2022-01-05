package com.adds.addon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com.adds.addon.*")
@ComponentScan(basePackages = "com.adds.addon.security")
@ComponentScan(basePackages = "com.adds.addon.component")
@ComponentScan(basePackages = "com.adds.addon.service.*")
@Configuration(value = "com.adds.addon.config")
@EntityScan(basePackages = "com.adds.addon.entities")
@EnableTransactionManagement
@EnableScheduling
@EnableWebSecurity
public class AddonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddonApplication.class, args);
    }

}
