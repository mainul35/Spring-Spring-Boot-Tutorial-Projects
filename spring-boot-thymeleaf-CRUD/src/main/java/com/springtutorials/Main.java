package com.springtutorials;

import com.springtutorials.Security.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Created by Mainul35 on 2/26/2017.
 */
@ComponentScan(basePackages = {
        "com.springtutorials.model",
        "com.springtutorials.Controller",
        "com.springtutorials.Service",
        "com.springtutorials.Repository"})

@EnableAutoConfiguration
@Import({WebSecurityConfig.class})
@SpringBootApplication(exclude = {com.springtutorials.Config.class})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}