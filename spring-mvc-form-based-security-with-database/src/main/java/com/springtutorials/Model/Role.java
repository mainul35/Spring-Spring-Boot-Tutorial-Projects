package com.springtutorials.Model;


import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority{

	private String authorityName;
	
	@Override
	public String getAuthority() {
		return this.authorityName;
	}

	public void setAuthority(String authorityName){
		this.authorityName = authorityName;
	}

	@Override
	public String toString() {
		return "Role [authorityName=" + authorityName + ", getAuthority()=" + getAuthority() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
