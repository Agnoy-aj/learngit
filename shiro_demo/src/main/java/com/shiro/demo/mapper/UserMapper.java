package com.shiro.demo.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shiro.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User>{

    public User findByUsername(@Param("username") String username);


}
