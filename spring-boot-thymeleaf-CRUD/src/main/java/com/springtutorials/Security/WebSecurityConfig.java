package com.springtutorials.Security;

import com.springtutorials.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthSuccessHandler authSuccessHandler;
    @Autowired
    DataSource dataSource;
    @Autowired
    UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()

                .authorizeRequests()
                .antMatchers("/contents/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**","/student/create", "/student/update", "/student/delete")
                .hasRole("ADMIN")
                .antMatchers("/student/show", "/dashboard")
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login-processing")
                .usernameParameter("username")
                .passwordParameter("password")
//                .defaultSuccessUrl("/")
                .successHandler(authSuccessHandler)
                .permitAll()
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
            .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

//#####################                   In Memory Authentication        #####################
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password("password").roles("ADMIN")
//                .and()
//                .withUser("user").password("password").roles("USER");

//#####################                   Jdbc Authentication             #####################

//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select u.`username`, u.`password`, u.`enabled` from `user` u where u.username=? ")
//                .authoritiesByUsernameQuery(
//                        "select u.`username`, a.`authority` from `user` u, `authority` a, `user_autrhority` ua where u.id = ua.user_id and a.id = ua.authority_id and u.username=? ");


//#####################     Custom UserDetailsService Authentication      #####################

        auth.userDetailsService(userService).passwordEncoder(BCPasswordEncoder());
    }

    @Bean
    public CustomAuthSuccessHandler authSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }

    @Bean
    PasswordEncoder BCPasswordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
}