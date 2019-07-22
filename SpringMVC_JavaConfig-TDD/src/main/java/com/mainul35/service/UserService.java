package com.mainul35.service;

import com.mainul35.model.User;
import com.mainul35.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }

    public void setUserRepository(UserRepository userRepositoryMock) {
        this.userRepository = userRepositoryMock;
    }
}
