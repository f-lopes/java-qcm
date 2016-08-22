package com.ingesup.java.qcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.ingesup.java.qcm")
@EnableJpaRepositories(basePackages = "com.ingesup.java.qcm.repository")
@EnableCaching
@SpringBootApplication
public class JavaQcmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaQcmApplication.class, args);
    }
}
