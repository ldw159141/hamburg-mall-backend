package com.ldw.controller;


import com.ldw.common.Result;
import com.ldw.dto.TypeQuery;
import com.ldw.dto.UserQuery;
import com.ldw.entity.Type;
import com.ldw.entity.User;
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
 * @since 2023-03-28
 */
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private RedisUtil redisUtil;




    @PostMapping("/page")
    public Result findPage(@RequestBody TypeQuery typeQuery){
        return Result.success( this.typeService.page(typeQuery));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Type type){
        typeService.saveOrUpdate(type);
        redisUtil.set("type_"+type.getId(),type);
        return Result.success();
    }


    @PostMapping("/delete")
    public  Result delete(@RequestBody List<Integer> ids){

        for (Integer id:ids){

            redisUtil.delete("type_"+id);
            System.out.println(id);
        }
        typeService.removeByIds(ids);

        return Result.success();
    }

}

