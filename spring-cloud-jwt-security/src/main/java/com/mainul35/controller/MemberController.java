package com.mainul35.controller;

import com.mainul35.domain.Member;
import com.mainul35.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberRepository applicationUserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Member user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setId(UUID.randomUUID().toString());
        applicationUserRepository.save(user);
    }
}
