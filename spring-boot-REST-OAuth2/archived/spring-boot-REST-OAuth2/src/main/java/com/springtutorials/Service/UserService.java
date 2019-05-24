package com.springtutorials.Service;

import com.springtutorials.Repository.UserRepository;
import com.springtutorials.model.Authority;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.springtutorials.model.User user = userRepository.findByUsername(username);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(
                "select a.id, a.authority, u.username " +
                "from `user` u, `authority` a, `user_autrhority` au " +
                "where u.id = au.user_id " +
                "and a.id = au.authority_id " +
                "and u.username = '"+username+"'");
        List<Authority> authorities = new ArrayList<>();

        for (Map<String, Object> map:maps) {
            Authority authority = new Authority();
            authority.setAuthority(map.get("authority").toString());
            authority.setUsername(map.get("username").toString());
            authority.setId(Integer.parseInt(map.get("id").toString()));
            authorities.add(authority);
        }
        user.setAuthorityList(authorities);

        User user1 = new User(user.getUsername(), user.getPassword(), authorities);

        System.out.println(user1.toString());
        return user1;
    }

    public boolean createUser(com.springtutorials.model.User user){
        if(!existsWithEmail(user.getEmail()) || !existsWithUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            System.out.println(user.toString());
            userRepository.save(user);
            String sql = "insert into user_autrhority(user_id, authority_id)\n" +
                    "select u.id, a.id\n" +
                    "from user u, authority a\n" +
                    "where u.username='" + user.getUsername() + "'\n" +
                    "and a.authority='ROLE_USER';";
            int res = jdbcTemplate.update(sql);

            return res == 1 ? true : false;
        }
        return false;
    }

    public boolean existsWithUsername(String username){
        if(userRepository.findByUsername(username) instanceof com.springtutorials.model.User){
            return true;
        }else{
            return false;
        }
    }

    public boolean existsWithEmail(String email){
        if(userRepository.findByUsername(email) instanceof com.springtutorials.model.User){
            return true;
        }else{
            return false;
        }
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
}
