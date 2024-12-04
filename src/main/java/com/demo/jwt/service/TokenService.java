package com.demo.jwt.service;



import org.springframework.stereotype.Service;
import java.security.*;
import org.json.JSONException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.*;


@Service
public class TokenService {

    public List<String> getToken(String token) throws JSONException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchProviderException {

        ArrayList<String> tokenresponse = new ArrayList<String>();

        String[] parts = token.split("\\.");

        for (int i = 0; i < 2; i++) {
            var partbase64 = parts[i].replaceAll("\\-", "+").replaceAll("\\_", "/");

            byte[] decodedBytes = Base64.getDecoder().decode(partbase64.getBytes(StandardCharsets.UTF_8));

        String decodedString = new String(decodedBytes);

            tokenresponse.add(decodedString);

        }

        if (parts[2] != null) {


            var partbase64 = parts[2].replaceAll("\\-", "+").replaceAll("\\_", "/");
            byte[] decodedBytes = Base64.getDecoder().decode(partbase64.getBytes(StandardCharsets.UTF_8));
            String decodedString = new String(decodedBytes);

            KeyFactory sr = KeyFactory.getInstance("DSA");


            tokenresponse.add(sr.getAlgorithm());

        }
        return tokenresponse;

    }

}

