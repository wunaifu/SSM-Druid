package com.wnf.controller;

import com.wnf.jedis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private JedisClient jedisClient;

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    @ResponseBody
    @RequestMapping("/jedis")
    public String jedis() {
        String json = "";
        //先查询缓存
        //添加缓存不能影响正常业务逻辑
        try {
            //查询缓存
            json = jedisClient.hget("wnfSet", "wnf");

        } catch (Exception e) {
            e.printStackTrace();
        }
        //把结果添加到缓存
        try {
            jedisClient.hset("wnfSet", "wnf", "测试，添加redis数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

}
