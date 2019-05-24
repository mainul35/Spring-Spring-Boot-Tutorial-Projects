package com.mainul35.service;

import com.mainul35.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    List<User> users = new ArrayList<>(0);

    public User save(User user){
        users.add(user);
        return user;
    }

    public List<User> getAllUsers(){
        return this.users;
    }

    public User getUser(String email){
        for (User u: users) {
            if (u.getEmail().equalsIgnoreCase(email)){
                return u;
            }
        }
        return null;
    }
    public List<User> updateUser(User user){
        boolean userExists = false;
        for (User u: users) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())){
                users.remove(u);
                users.add(user);
                System.out.println(user.toString());
                userExists = true;
            }else {
                userExists = false;
            }
        }

        return userExists == true?users:null;
    }

    public List<User> removeUser(User user){
        boolean userExists = false;
        for (User u: users) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())){
                users.remove(u);
                userExists = true;
            }else {
                userExists = false;
            }
        }
        return userExists == true?users:null;
    }
}
