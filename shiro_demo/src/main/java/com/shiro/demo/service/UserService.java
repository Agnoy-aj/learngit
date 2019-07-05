package com.shiro.demo.service;

import com.shiro.demo.domain.User;

public interface UserService {
    User findByName(String username);

    User findById(Integer id);
}
