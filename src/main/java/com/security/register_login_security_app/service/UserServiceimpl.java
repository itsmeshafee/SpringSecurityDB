package com.security.register_login_security_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.register_login_security_app.entity.User;
import com.security.register_login_security_app.repository.UserRepository;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        String passowrd = passwordEncoder.encode(user.getPassword());
        user.setPassword(passowrd);
        user.setRole("ROLE_USER");
        User newUser = userRepository.save(user);
        return newUser;

    }

    
}
