package com.ldw.controller;


import com.ldw.common.Result;
import com.ldw.dto.AddressQuery;
import com.ldw.dto.RoleQuery;
import com.ldw.entity.Address;
import com.ldw.entity.Role;
import com.ldw.service.AddressService;
import com.ldw.service.RoleService;
import com.ldw.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/address")
public class AddressController {


    @Autowired
    private AddressService addressService;

    @Autowired
    private RedisUtil redisUtil;
    @PostMapping("/page")
    public Result findPage(@RequestBody AddressQuery addressQuery){
        return Result.success( this.addressService.page(addressQuery));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Address address){
        addressService.saveOrUpdate(address);
        redisUtil.set("address_"+address.getId(),address);
        return Result.success();
    }


    @PostMapping("/delete")
    public  Result delete(@RequestBody List<Integer> ids){

        for (Integer id:ids){

            redisUtil.delete("address_"+id);
            System.out.println(id);
        }
        addressService.removeByIds(ids);

        return Result.success();
    }
}

