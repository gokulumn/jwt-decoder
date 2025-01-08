package com.demo.jwt.controller;


import com.demo.jwt.model.Jwtdel;
import com.demo.jwt.model.Respjwt;
import com.demo.jwt.service.TokenService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;


@RestController
@RequestMapping("/jwt")
public class Jwtoller {

    @Autowired
    private TokenService tokenservice;

    @PostMapping(produces = "application/json")
    public Respjwt jwtParser(@RequestBody Jwtdel jwtdel, Respjwt respjwt) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, SignatureException, InvalidKeyException, JSONException {

        return tokenservice.getToken(jwtdel, respjwt);
    }

}


