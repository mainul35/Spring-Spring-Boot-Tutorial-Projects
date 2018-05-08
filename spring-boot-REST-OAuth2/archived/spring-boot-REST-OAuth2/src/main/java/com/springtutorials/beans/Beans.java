package com.springtutorials.beans;

import com.springtutorials.JdbcManager.JdbcManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
public class Beans {

    @Autowired
    @Bean JdbcTemplate jdbcTemplate(DriverManagerDataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @Bean
    JdbcManagerService jdbcManagerService(JdbcTemplate jdbcTemplate){
        return new JdbcManagerService(jdbcTemplate);
    }
}
