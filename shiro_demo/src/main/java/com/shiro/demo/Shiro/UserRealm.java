package com.shiro.demo.Shiro;

import com.shiro.demo.domain.User;
import com.shiro.demo.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.shiro.demo.service.UserService;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;


public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService UserService;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
//        info.addStringPermission("user:add");
        Subject subject= SecurityUtils.getSubject();
        User user=(User) subject.getPrincipal();
        User dbUser= UserService.findById(user.getId());

        info.addStringPermission(dbUser.getPermission());

        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;

        User user= UserService.findByName(token.getUsername());
        if(user==null){
            //用户名不存在
            System.out.println("用户名不存在");
            //shiro底层会抛出UnKnowAccountException
            return null;
        }

        //2.判断密码

        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
