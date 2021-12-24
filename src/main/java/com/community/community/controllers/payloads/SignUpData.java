package com.community.community.controllers.payloads;

import java.util.List;

public class SignUpData {
    public String username;
    public String password;
    public List<String> roles;

    public SignUpData(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
