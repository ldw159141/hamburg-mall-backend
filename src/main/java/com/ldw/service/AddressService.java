package com.ldw.service;

import com.ldw.dto.AddressQuery;
import com.ldw.dto.TypeQuery;
import com.ldw.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldw.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liudewei
 * @since 2023-04-21
 */
public interface AddressService extends IService<Address> {

    public PageVO page(AddressQuery addressQuery);

}
