package com.springtutorials.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.springtutorials.Model.PasswordHashingUtil;
import com.springtutorials.Model.User;
import com.springtutorials.Service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordHashingUtil passwordHashingUtil;
	
	public void setUserService(UserService userService) {
        this.userService = userService;
    }
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		User user = (User)userService.loadUserByUsername(username);
		
		if(user==null){
			throw new BadCredentialsException("Invalid username.");
		}
		try{
			if(!passwordHashingUtil.validatePassword(password, user.getPassword())){
				throw new BadCredentialsException("Invalid Password.");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
