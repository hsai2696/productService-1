package com.productservice.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("UserService")
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public Boolean checkUserExist(String email){
        Boolean userExists = restTemplate.getForObject("http://userservice/users/" + email, Boolean.class);
        return userExists;
    }
}