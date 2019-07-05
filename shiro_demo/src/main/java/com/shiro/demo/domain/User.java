package com.shiro.demo.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "test_shiro")
public class User implements Serializable{

    private  Integer id;
    private  String username;
    private  String password;
    private  String permission;
}
