package com.mainul35.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Collection;
import java.util.UUID;

@Configuration
public class BeanConfiguration {

    @Bean
    @Qualifier("oauth2AccessToken")
    public DefaultOAuth2AccessToken defaultOAuth2AccessToken(){
        return new DefaultOAuth2AccessToken(UUID.randomUUID().toString());
    }

    public OAuth2Authentication oAuth2Authentication() {
        return new OAuth2Authentication();
        JdbcTokenStore
    }
//
//    public OAuth2Request oAuth2Request(){
//        String generatedClientId = "oauthClient";
//        return new OAuth2Request(generatedClientId);
//    }

    @Bean
    public SessionFactory sessionFactory() {
        try {
            SessionFactory sessionFactory = new AnnotationConfiguration().buildSessionFactory();
            return sessionFactory;
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
