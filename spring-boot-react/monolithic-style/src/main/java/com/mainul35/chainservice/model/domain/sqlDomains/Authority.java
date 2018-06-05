package com.mainul35.chainservice.model.domain.sqlDomains;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name="authority")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_uuid", nullable = false, length = 11)
    Long id;

    @Column(name = "authority", unique = true, nullable = false, length = 250)
    String authority;

    public Authority() {
		// TODO Auto-generated constructor stub
	}
    
    public Authority(Long id, String authority){
    	this.id = id;
    	this.authority = authority;
    }
    
    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                '}';
    }
}