package com.demo.jwt.controller;


import com.demo.jwt.model.Jwtdel;
import com.demo.jwt.service.TokenService;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class Jwtoller {

    @Autowired
    private TokenService tokenservice;


    @PostMapping
    public List<String> tokenService(@RequestBody Jwtdel token) throws JSONException, ParseException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchProviderException {
        return tokenservice.getToken(token.getToken());
    }

}


