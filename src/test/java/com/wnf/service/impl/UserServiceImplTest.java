package com.wnf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wnf.dao.UserDao;
import com.wnf.pojo.User;
import com.wnf.pojo.UserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class UserServiceImplTest {

    @Autowired
    UserDao userDao;

    @Test
    public void userList2() {
//设置分页信息,获取第page页，rows条内容，默认查询总数count
        PageHelper.startPage(1,3);
        //执行查询,紧跟着的第一个select方法会被分页
        UserExample userExample = new UserExample();
        List<User> userList = userDao.selectByExample(userExample);
        System.out.println("userList=" + userList);
        //取查询结果,用PageInfo对结果进行包装
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        System.out.println("pageInfo=" + pageInfo);
    }

    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
        //获得Mapper的代理对象
        UserDao itemMapper = applicationContext.getBean(UserDao.class);
        //设置分页信息
        PageHelper.startPage(1, 30);
        //执行查询
        UserExample example = new UserExample();
        List<User> list = itemMapper.selectByExample(example);
        //取分页信息
        PageInfo<User> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        System.out.println(pageInfo.getPageNum());
        System.out.println(pageInfo.getPageSize());
    }
}