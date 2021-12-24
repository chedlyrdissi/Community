package com.community.community.usermanagement.repository;

import com.community.community.usermanagement.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findByUsername(String username);
    public boolean existsByUsername(String username);
}
