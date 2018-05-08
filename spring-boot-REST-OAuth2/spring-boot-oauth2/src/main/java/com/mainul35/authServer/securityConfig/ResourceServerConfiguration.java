package com.mainul35.authServer.securityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "my_rest_api";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.anonymous()
		.disable()
		.requestMatchers()
		.antMatchers("/api/**", "/apiv2/**")
		.and()
		.authorizeRequests()
				.antMatchers("/api/**").access("hasRole('ADMIN')")
				.antMatchers("/apiv2/**").access("hasRole('USER')")
				.and()
				.exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}