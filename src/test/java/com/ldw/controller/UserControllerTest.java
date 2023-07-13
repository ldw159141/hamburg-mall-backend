package com.ldw.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldw.common.Result;
import com.ldw.dto.UserQuery;
import com.ldw.entity.User;
import com.ldw.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserService userService;
    @Test
      void  findPage(){

        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByDesc(User::getId);
        UserQuery userQuery=new UserQuery();
        userQuery.setUsername("t");
        userQuery.setPageNum(1);
        userQuery.setPageSize(2);

        if (!"".equals(userQuery.getUsername()) && userQuery.getUsername()!=null){
            wrapper.like(User::getUsername,userQuery.getUsername());
        }

        Page<User> page = userService.page(
                new Page<>(
                        userQuery.getPageNum(),
                        userQuery.getPageSize()
                ),wrapper
        );
        System.out.println(page);

    }

    @Test
    void save() {
        userService.selectByUsername("123");
    }
}