package com.community.community.usermanagement.repository;

import com.community.community.usermanagement.entity.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole, String> {
    @Override
    boolean existsById(String role);

    List<UserRole> findByRoleIn(List<String> roles);
}
