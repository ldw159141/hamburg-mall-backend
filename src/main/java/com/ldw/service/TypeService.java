package com.ldw.service;

import com.ldw.dto.TypeQuery;
import com.ldw.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldw.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liudewei
 * @since 2023-03-28
 */
public interface TypeService extends IService<Type> {

    public PageVO page(TypeQuery typeQuery);

}
