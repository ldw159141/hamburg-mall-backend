package com.ldw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldw.dto.UserLoginDTO;
import com.ldw.entity.User;
import com.ldw.mapper.UserMapper;
import com.ldw.service.UserService;
import com.ldw.util.JwtUtils;
import com.ldw.util.ResultVOUtil;
import com.ldw.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liudewei
 * @since 2023-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultVO login(UserLoginDTO user){
    //   LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>();
    //    userLambdaQueryWrapper.eq(User::getUsername, user.getUsername())
    //           .eq(User::getPassword, user.getPassword()).last("limit 1");
       QueryWrapper<User> userLambdaQueryWrapper = new QueryWrapper<>();
        userLambdaQueryWrapper.eq("username", user.getUsername()).eq("password", user.getPassword());

        User userInfo = this.userMapper.selectOne(userLambdaQueryWrapper);

        if (userInfo != null) {
            String token = JwtUtils.generateTokenByUser(userInfo);

            HashMap<Object, Object> map = new HashMap<>();
            map.put("token",token);
            map.put("username",userInfo.getUsername());
            map.put("roleId",userInfo.getRoleId());
          return ResultVOUtil.successSetMsg(map,"登录成功",200);
          //  return Result.success(map);
        } else {

          return ResultVOUtil.failSetMsg("请检查用户密码信息是否错误");
           // return Result.error("账号或者密码错误");
        }

    }
    }

