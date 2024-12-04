package com.demo.jwt.model;


public class Jwtdel {

    private String token;

    // No-argument constructor
    public Jwtdel() {
    }

    // All-argument constructor (optional, for convenience)
//    public Jwtdel(String token) {
//        this.token = token;
//    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
