package com.ldw.service.impl;

import com.ldw.entity.User;
import com.ldw.mapper.UserMapper;
import com.ldw.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserServiceImplTest {
  @Autowired
  private UserService userService;
  @Autowired
  private UserMapper userMapper;
    @Test
    void updateUserInfo() {
        User user=new User();
        user.setId(2);
        user.setUsername("test22");
        user.setPassword("test22");
        user.setRoleId(1);
        this.userService.updateUserInfo(user);
//        userMapper.insert(user);
//        userMapper.updateById(user);
    //    System.out.println(user);
    }


}