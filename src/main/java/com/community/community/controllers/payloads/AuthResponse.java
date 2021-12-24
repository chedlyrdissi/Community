package com.community.community.controllers.payloads;

import java.util.List;

public class AuthResponse {
    public String token;
    public long id;
    public String username;
    public List<String> roles;
    public AuthResponse(String token, long id, String username, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
