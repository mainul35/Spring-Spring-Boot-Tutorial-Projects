package com.springtutorials;

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
        "com.springtutorials.Service",
        "com.springtutorials.Repository",
        "com.springtutorials.Rest",
        "com.springtutorials.Security.oauth2"})

@EnableAutoConfiguration
@SpringBootApplication(exclude = {com.springtutorials.Config.class})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}