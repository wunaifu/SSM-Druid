package com.wnf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wnf.dao.UserDao;
import com.wnf.pojo.User;
import com.wnf.pojo.UserExample;
import com.wnf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wunaifu on 2018/7/28.
 */
@Service
public class UserServiceImpl implements UserService {

    //注入Dao实现类依赖
    //    @Resource
    @Autowired
    private UserDao userDao;

    public String addUser(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(user.getPhone());
        List<User> userList = userDao.selectByExample(userExample);
        if (userList != null && userList.size() > 0) {
            return "user_exist";
        } else {

            int result = userDao.insertSelective(user);

            if (result == 1) {
                return "register_success";
            } else {
                return "register_failure";
            }
        }
    }
    public List<User> userList() {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        List<User> userList = userDao.selectByExample(userExample);
        return userList;
    }

    public PageInfo userListPager(int page, int rows) {
        //设置分页信息,获取第page页，rows条内容，默认查询总数count
        PageHelper.startPage(page,rows);
        //执行查询,紧跟着的第一个select方法会被分页
        UserExample userExample = new UserExample();
        List<User> userList = userDao.selectByExample(userExample);
        System.out.println("userList="+userList);
        //取查询结果,用PageInfo对结果进行包装
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    public List<User> userList2(int pageNo,int pageSize,int startRow) {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("user_id desc,phone desc");
//        userExample.setStartRow(startRow);
//        userExample.setPageSize(pageSize);
//        userExample.setPageNo(pageNo);
//        List<User> userList = userDao.selectByExampleWithBLOBs(userExample);
        List<User> userList = userDao.selectByExample(userExample);

        return userList;
    }

}
