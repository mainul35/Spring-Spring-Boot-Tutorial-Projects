package com.mainul35.chainservice.model.domain.sqlDomains;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

@Entity
@Table(name = "user")
public class UserEntity implements UserDetails {
    @Id
    @Column(name="user_uuid", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name="username", unique = true, nullable = false, length = 250)
    String username;
    @Column(name = "password", nullable = false, length = 250)
    String password;
    @Column(name = "email", nullable = false, unique = true, length = 250)
    String email;
    @Column(name = "name", nullable = false, length = 250)
    String name;
    
    @ManyToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_Id", referencedColumnName = "user_uuid"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "role_uuid"))
    List<Authority> authorityList;
    
    @Column(name = "enabled", nullable = false, length = 45)
    boolean enabled = true;

    public UserEntity() {
		// TODO Auto-generated constructor stub
	}
    
    public UserEntity(Long id, String name, String password, String email, String username, boolean enabled, Authority authority) {
		// TODO Auto-generated constructor stub
    	this.name = name;
    	this.id = id;
    	this.password = password;
    	this.email = email;
    	this.username = username;
    	this.enabled = enabled;
    	this.authorityList = new ArrayList<Authority>();
    	this.authorityList.add(authority);
	}
    

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", authorityList=" + authorityList +
                ", enabled=" + enabled +
                '}';
    }
}
