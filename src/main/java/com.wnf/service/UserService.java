package com.wnf.service;

import com.wnf.pojo.User;

import java.util.List;

/**
 * Created by wunaifu on 2018/7/28.
 * 定义方法，只需要定义，实现在对应的*ServiceImpl.java里实现，这就是spring做的工作
 */
public interface UserService {
    String addUser(User user);

    List<User> userList();
    List<User> userList2(int pageNo,int pageSize,int startRow);
}