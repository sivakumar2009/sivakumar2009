package com.demantiaapp.auth.service;

import com.demantiaapp.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
