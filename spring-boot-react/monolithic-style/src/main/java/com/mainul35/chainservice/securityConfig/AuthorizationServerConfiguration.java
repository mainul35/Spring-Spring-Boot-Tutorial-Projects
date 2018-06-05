package com.mainul35.chainservice.securityConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private static String REALM = "MY_OAUTH_REALM";

	@Value("classpath:schema.sql")
	private Resource schemaScript;

	 @Autowired
	 private TokenStore tokenStore;

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

//		clients.jdbc(dataSource());
		clients.inMemory()
		.withClient("trusted-web-client")
				.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
				.authorities("ROLE_TRUSTED_WEB_CLIENT")
				.scopes("trust")
				.secret("secret")
				.accessTokenValiditySeconds(60 * 60)
				.refreshTokenValiditySeconds(60 * 60 * 24)
				.redirectUris("http://localhost:8090/")
				.resourceIds("/api/trusted-web-client")
				;
	}

//	private DataSource dataSource() {
//		// TODO Auto-generated method stub
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl(
//				"jdbc:mysql://localhost:3306/chainservice?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false");
//		dataSource.setUsername("root");
//		dataSource.setPassword("");
//		return dataSource;
//	}
//
//	@Bean
//	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
//		DataSourceInitializer initializer = new DataSourceInitializer();
//		initializer.setDataSource(dataSource);
//		initializer.setDatabasePopulator(databasePopulator());
//		return initializer;
//	}
//
//	private DatabasePopulator databasePopulator() {
//		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//		populator.addScript(schemaScript);
//		return populator;
//	}
//
//	@Bean
//	public TokenStore tokenStore() {
//		return new JdbcTokenStore(dataSource());
//	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		 endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
		 .authenticationManager(authenticationManager);
//		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		 oauthServer.realm(REALM+"/client");
//		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

//	@Primary
//	@Bean
//	public RemoteTokenServices tokenService() {
//		RemoteTokenServices tokenService = new RemoteTokenServices();
//		tokenService.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
//		tokenService.setClientId("fooClientIdPassword");
//		tokenService.setClientSecret("secret");
//		return tokenService;
//	}
}