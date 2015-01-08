package com.ingesup.java.qcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.ingesup.java.qcm.repository")
public class JavaQcmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaQcmApplication.class, args);
    }
}
