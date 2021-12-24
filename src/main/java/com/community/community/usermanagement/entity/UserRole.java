package com.community.community.usermanagement.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class UserRole implements GrantedAuthority {
    @Id
    private String role;

    public UserRole() {}

    public UserRole(String role) {this.role = role;}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(role, userRole.role);
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
