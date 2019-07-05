package com.shiro.demo;


import com.shiro.demo.domain.User;
import com.shiro.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private DataSource dataSource;

    public UserMapper UserMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void showDataSource() throws SQLException {
		System.out.println(dataSource);
		Connection conn=dataSource.getConnection();
		System.out.println(conn);
	}

    @Resource
	private UserMapper userMapper;
	@Test
    public void testSelect(){
    User user=userMapper.findByUsername("root");
        System.out.println(user);
    }
}
