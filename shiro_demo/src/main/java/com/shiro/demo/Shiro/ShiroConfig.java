package com.shiro.demo.Shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 创建shiroFilterFactoryBean
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        /**
         * shiro内置过滤器，可以实现权限相关的拦截器
         *      常用的过滤器
         *      anon:无需认证（登录）可以访问
         *      authc:必须认证才可以访问
         *      user:如果使用rememberMe的功能可以直接访问
         *      perms:该资源必须得到资源权限才可以访问
         *      role:该资源必须得到角色权限才可以访问
         */

        Map<String,String> filterMap=new LinkedHashMap<>();
        filterMap.put("/hello", "anon");
        filterMap.put("/testThymtleaf", "anon");
        //放行login.html页面
        filterMap.put("/login", "anon");

        filterMap.put("/*","authc");
        //授权过滤器
        //注意：当前授权拦截后，shiro会自动跳转到未授权页面
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");

        //修改跳转的未授权的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");


        //修改跳转的登陆页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        return shiroFilterFactoryBean;

    }


    /**
     * 创建DefaultWebSecurityManager
     * @return
     */
    @Bean(value = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);

        return securityManager;

    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

}
