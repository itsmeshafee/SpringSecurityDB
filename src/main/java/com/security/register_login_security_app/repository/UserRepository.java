package com.security.register_login_security_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.register_login_security_app.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
    
}
