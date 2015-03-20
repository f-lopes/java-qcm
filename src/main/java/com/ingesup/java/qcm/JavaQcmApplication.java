package com.ingesup.java.qcm;

import com.ingesup.java.qcm.config.DataConfig;
import com.ingesup.java.qcm.config.SecurityConfig;
import com.ingesup.java.qcm.config.WebMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.ingesup.java.qcm")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.ingesup.java.qcm.repository")
@Import(value = {WebMvcConfig.class, SecurityConfig.class, DataConfig.class})
public class JavaQcmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaQcmApplication.class, args);
    }
}
