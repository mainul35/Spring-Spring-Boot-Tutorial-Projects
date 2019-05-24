package com.mainul35.chainservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainul35.chainservice.model.domain.sqlDomains.Authority;
import com.mainul35.chainservice.repositories.sqlRepositories.AuthorityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    public List<Authority> findAll(){
        List<Authority> authorities = new ArrayList();
        authorityRepository.findAll().forEach(authorities::add);
        return authorities;
    }

    public boolean save(Authority authority) {
        List<Authority> authorities = findAll();

        for (Authority a:authorities) {
            if(a.getAuthority().equalsIgnoreCase(authority.getAuthority())){
                return false;
            }
        }
        authorityRepository.save(authority);
        return true;
    }

}