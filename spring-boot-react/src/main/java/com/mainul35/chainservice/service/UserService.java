package com.mainul35.chainservice.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mainul35.chainservice.model.domain.sqlDomains.Authority;
import com.mainul35.chainservice.model.domain.sqlDomains.UserEntity;
import com.mainul35.chainservice.repositories.sqlRepositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UserEntity user = userRepository.findByUsername(username);
    	System.out.println(user.toString());
    	return user;
    }

    public void createUser(UserEntity user){
        if(!existsWithEmail(user.getEmail()) || !existsWithUsername(user.getUsername())) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
            System.out.println(user.toString());
            userRepository.save(user);
        }
    }

    public List<UserEntity> findAll(){
    	return userRepository.findAll();
    }
    
    public boolean existsWithUsername(String username){
        if(userRepository.findByUsername(username) instanceof UserEntity){
            return true;
        }else{
            return false;
        }
    }

    public boolean existsWithEmail(String email){
        if(userRepository.findByUsername(email) instanceof UserEntity){
            return true;
        }else{
            return false;
        }
    }

	public boolean save(
			UserEntity user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
		return true;
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}
}
