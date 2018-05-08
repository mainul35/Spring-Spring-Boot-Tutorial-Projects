package com.mainul35.authServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.mainul35.authServer.securityConfig.SecurityWebApplicationInitializer;

@SpringBootApplication
@Import(value={SecurityWebApplicationInitializer.class})
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

}
