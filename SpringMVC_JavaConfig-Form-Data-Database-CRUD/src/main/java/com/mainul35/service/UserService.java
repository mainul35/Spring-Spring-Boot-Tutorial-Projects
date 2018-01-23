package com.mainul35.service;

import com.mainul35.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User save(User user) {
        jdbcTemplate.update(
                "INSERT INTO tbl_user(id, name, email, age, sex, country, phoneNumber) VALUES (?,?,?,?,?,?,?)",
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getSex(),
                user.getCountry(),
                user.getPhoneNumber());

        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>(0);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("SELECT * FROM tbl_user");
        for (Map<String, Object> map : mapList) {
            User user = new User();
            user.setId(Long.parseLong(map.get("id").toString()));
            user.setName(map.get("name").toString());
            user.setEmail(map.get("email").toString());
            user.setAge(Integer.parseInt(map.get("age").toString()));
            user.setSex(map.get("sex").toString());
            user.setCountry(map.get("country").toString());
            user.setPhoneNumber(map.get("phoneNumber").toString());
            users.add(user);
        }
        return users;
    }

    public User getUser(String email) {
        for (User u : getAllUsers()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public List<User> updateUser(User user) {
        jdbcTemplate.update("UPDATE tbl_user SET name = ?, age = ?, sex=?, country=?, phoneNumber=? WHERE email = ?",
                user.getName(), user.getAge(), user.getSex(), user.getCountry(), user.getPhoneNumber(), user.getEmail());
        return getAllUsers();
    }

    public List<User> removeUser(User user) {
        jdbcTemplate.update("DELETE FROM tbl_user WHERE email=?", user.getEmail());
        return getAllUsers();
    }
}
