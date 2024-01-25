package com.security.register_login_security_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.security.register_login_security_app.entity.User;
import com.security.register_login_security_app.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User newUser = userRepository.findByEmail(username);
        if (newUser == null) {
            throw new UsernameNotFoundException("User Name Not Found !!");
        }else{
            return new CustomUser(newUser);
        }
        
        
    }
    
}
