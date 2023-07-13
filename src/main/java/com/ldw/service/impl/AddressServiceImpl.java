package com.ldw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldw.dto.AddressQuery;
import com.ldw.entity.Address;
import com.ldw.entity.Type;
import com.ldw.mapper.AddressMapper;
import com.ldw.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldw.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liudewei
 * @since 2023-04-21
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Override
    public PageVO page(AddressQuery addressQuery) {
        LambdaQueryWrapper<Address> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Address::getId);

        if (!"".equals(addressQuery.getName()) && addressQuery.getName()!=null){
            wrapper.like(Address::getName,addressQuery.getName());
        }

        Page<Address> page = addressMapper.selectPage(
                new Page<>(
                        addressQuery.getPageNum(),
                        addressQuery.getPageSize()
                ),wrapper
        );
        return new PageVO(page);

    }
}
