package com.mainul35.springbootjaxrs.service;

import com.mainul35.springbootjaxrs.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    private final List<User> userList;

    public UserService() {
        userList = Stream.of("mainul35", "mainul36").map(s -> {
            User user = new User();
            user.setUsername(s);
            user.setUuid(UUID.randomUUID().toString());
            user.setEmail(s.concat("@gmail.com"));
            user.setName(s);
            return user;
        }).collect(Collectors.toList());
    }

    public List<User> getAll () {
        return userList;
    }

    public User getByUsername(String username) {
        return userList.stream().filter(user -> user.getUsername().equals(username)).findFirst().get();
    }
}
