package com.springtutorials.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @Column(name="id", nullable = false, length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(name="username", unique = true, nullable = false, length = 250)
    String username;
    @Column(name = "password", nullable = false, length = 250)
    String password;
    @Column(name = "email", nullable = false, unique = true, length = 250)
    String email;
    @Column(name = "name", nullable = false, length = 250)
    String name;
//    @JoinTable(name = "user_authority",
//            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    @Transient
    List<Authority> authorityList;
    @Column(name = "enabled", nullable = false, length = 45)
    boolean enabled = true;

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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
