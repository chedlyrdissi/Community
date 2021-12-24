package com.community.community.controllers.payloads;

public class SignInData {

    public String username;
    public String password;

    public SignInData(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
