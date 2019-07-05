package com.shiro.demo.service;

import com.shiro.demo.domain.User;
import com.shiro.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User findByName(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }
}
