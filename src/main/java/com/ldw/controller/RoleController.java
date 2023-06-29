package com.ldw.controller;


import com.ldw.common.Result;
import com.ldw.dto.RoleQuery;
import com.ldw.dto.TypeQuery;
import com.ldw.entity.Role;
import com.ldw.entity.Type;
import com.ldw.service.RoleService;
import com.ldw.service.TypeService;
import com.ldw.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisUtil redisUtil;
    @PostMapping("/page")
    public Result findPage(@RequestBody RoleQuery roleQuery){
        return Result.success( this.roleService.page(roleQuery));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Role role){
        roleService.saveOrUpdate(role);
        redisUtil.set("role_"+role.getId(),role);
        return Result.success();
    }


    @PostMapping("/delete")
    public  Result delete(@RequestBody List<Integer> ids){

        for (Integer id:ids){

            redisUtil.delete("role_"+id);
            System.out.println(id);
        }
        roleService.removeByIds(ids);

        return Result.success();
    }

}

