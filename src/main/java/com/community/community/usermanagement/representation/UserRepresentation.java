package com.community.community.usermanagement.representation;

import org.springframework.hateoas.RepresentationModel;
import java.util.List;

public class UserRepresentation extends RepresentationModel<UserRepresentation> {
    private long id;
    private String username;
    private List<String> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
