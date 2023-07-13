package com.ldw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldw.dto.GoodsQuery;
import com.ldw.dto.UserQuery;
import com.ldw.entity.Goods;
import com.ldw.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liudewei
 * @since 2023-03-28
 */
public interface GoodsService extends IService<Goods> {

    public PageVO page(GoodsQuery goodsQuery);

}
