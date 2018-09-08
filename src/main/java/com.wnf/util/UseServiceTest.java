package com.wnf.util;

import com.wnf.dao.UserDao;
import com.wnf.pojo.User;
import com.wnf.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 普通类调用service层接口
 * Main方式无法调用，其他已注解类可调用
 * Created by wunaifu on 2018/9/8.
 */
public class UseServiceTest {

//    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-service.xml");
//    UserService userService = (UserService) ac.getBean(UserService.class);

    UserService userService = (UserService) SpringUtils.getBean(UserService.class);

    public void p() {
        System.out.println(userService.userList());
    }


    public List<User> getUserList() {
        return userService.userList();
    }

    public static void main(String[] args) {
        UseServiceTest t = new UseServiceTest();
        System.out.println(t.getUserList());
    }
}
