package com.ldw.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ldw.common.Result;
import com.ldw.dto.UserLoginDTO;
import com.ldw.entity.User;
import com.ldw.service.UserService;
import com.ldw.util.JwtUtils;
import com.ldw.util.ResultVOUtil;
import com.ldw.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liudewei
 * @since 2023-04-21
 */

@RestController
@Api(value = "登录模块",tags = "登录模块")
@RequestMapping("/user")
public class UserController {

    /***
     * 用户登录没写service
     */

    @Autowired
    private UserService userService;
    @ResponseBody
    @PostMapping("/login")

    public ResultVO login(@RequestBody UserLoginDTO user) {
       return this.userService.login(user);
    }

}



