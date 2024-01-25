package com.security.register_login_security_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService getDetailsService(){
        return new CustomUserDetailsService();
            
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(getDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(requests -> requests.requestMatchers("/","/saveUser","/signin","/login","/register")
        .permitAll()
        .requestMatchers("/user/**").authenticated())
        .formLogin(login -> login.loginPage("/signin")
        .loginProcessingUrl("/userLogin")
        .defaultSuccessUrl("/user/profile")
        .permitAll()).logout(logout->logout.logoutSuccessUrl("/userlogout"));

        // http.csrf(csrf->csrf.disable())
        // .authorizeHttpRequests(requests->requests
        // .requestMatchers("/","/signin","/userLogin","/register").permitAll()
        // .requestMatchers("/user/**").authenticated())
        // .formLogin(login->login.loginPage("/signin")
        // .loginProcessingUrl("/userLogin")
        // .defaultSuccessUrl("user/profile").permitAll());
        return http.build();
    }
    
}
