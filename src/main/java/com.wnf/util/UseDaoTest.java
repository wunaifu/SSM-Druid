package com.wnf.util;

import com.wnf.dao.UserDao;
import com.wnf.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 普通类调用dao层接口
 * Created by wunaifu on 2018/9/8.
 */
public class UseDaoTest {

    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-dao.xml");
    UserDao userDao = (UserDao) ac.getBean(UserDao.class);

    public void p() {
        System.out.println(userDao.selectByPrimaryKey(1));
    }

    public User getUser(int id) {
        return userDao.selectByPrimaryKey(id);
    }

    public static void main(String[] args) {
        UseDaoTest t = new UseDaoTest();
        System.out.println(t.getUser(2));
    }
}
