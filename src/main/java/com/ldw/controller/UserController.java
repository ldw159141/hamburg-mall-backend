package com.ldw.controller;


import com.ldw.common.Result;
import com.ldw.dto.UserLoginDTO;
import com.ldw.dto.UserQuery;
import com.ldw.entity.User;
import com.ldw.service.UserService;
import com.ldw.util.RedisUtil;
import com.ldw.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//    @Autowired
//    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @ResponseBody
    @PostMapping("/login")
    public ResultVO login(@RequestBody UserLoginDTO user) {
       return this.userService.login(user);
    }

    /**
     * 查询列表，没使用
     * @return
     */
    @GetMapping("/list")
    public Result list(){
        return Result.success(this.userService.list());
    }

    /**
     * 用户分页模糊查询
     * @param userQuery
     * @return
     */
    @PostMapping("/page")
    public Result findPage(@RequestBody UserQuery userQuery){
        return Result.success( this.userService.page(userQuery));
    }

    /**
     * 更新或者修改，判断条件id，
     * 新增没有传id，修改有传id
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        userService.saveOrUpdate(user);
      //  redisTemplate.opsForValue().set("user_"+user.getId(),user);
        redisUtil.set("user_"+user.getId(),user);
        return Result.success();
    }

    /**
     * 根据ids批量删除
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public  Result delete(@RequestBody List<Integer> ids){

        for (Integer id:ids){
         //   redisTemplate.delete("user_"+id);
            redisUtil.delete("user_"+id);
            System.out.println(id);
        }
        userService.removeByIds(ids);

        return Result.success();
    }



}




