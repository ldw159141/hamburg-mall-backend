package com.ldw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldw.common.Result;
import com.ldw.dto.UserLoginDTO;
import com.ldw.dto.UserQuery;
import com.ldw.entity.User;
import com.ldw.mapper.UserMapper;
import com.ldw.service.UserService;
import com.ldw.util.JwtUtils;
import com.ldw.util.ResultVOUtil;
import com.ldw.vo.PageVO;
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

    /**
     * 登录，返回tokenId，username，roleId，然后通过枚举前端返回roleName
     * @param user
     * @return
     */
    @Override
    public ResultVO login(UserLoginDTO user){
    //   LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>();
    //    userLambdaQueryWrapper.eq(User::getUsername, user.getUsername())
    //           .eq(User::getPassword, user.getPassword()).last("limit 1");
       QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", user.getUsername()).eq("password", user.getPassword()).last("limit 1");

        User userInfo = this.userMapper.selectOne(userQueryWrapper);

        if (userInfo != null) {
            String token = JwtUtils.generateTokenByUser(userInfo);

            HashMap<Object, Object> map = new HashMap<>();
            map.put("token",token);
            map.put("username",userInfo.getUsername());
            map.put("roleId",userInfo.getRoleId());
          return ResultVOUtil.successSetMsg(map,"登录成功",200);
          //  return Result.success(map);
        } else {

          return ResultVOUtil.failSetMsg("请检查账号密码信息是否错误",500);
           // return Result.error("账号或者密码错误");
        }


    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @Override
    public boolean updateUserInfo(User user) {
        return userMapper.updateById(user) > 0;
    }

    /**
     * 自定义id新增用户
     * @param user
     */
    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }


    /**
     * 用户分页查询，根据username模糊查询
     * @param userQuery
     * @return
     */
    @Override
    public PageVO page(UserQuery userQuery) {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByAsc(User::getId);

        if (!"".equals(userQuery.getUsername()) && userQuery.getUsername()!=null){
            wrapper.like(User::getUsername,userQuery.getUsername());
        }

        Page<User> page = userMapper.selectPage(
                new Page<>(
                        userQuery.getPageNum(),
                        userQuery.getPageSize()
                ),wrapper
        );
        return new PageVO(page);
    }
}

