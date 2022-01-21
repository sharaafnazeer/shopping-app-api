package com.creativelabs.shoppingapi.services;

import com.creativelabs.shoppingapi.entities.User;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {
    User save(User user);
    boolean existsByEmail(String email);
}
