package com.ldw.service;

import com.ldw.entity.Goodsimg;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldw.vo.GoodsVO;
import com.ldw.vo.ResultVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liudewei
 * @since 2023-03-29
 */

public interface GoodsimgService extends IService<Goodsimg> {
    public ResultVO HotGoodsList();

    public ResultVO GoodsList();

    public ResultVO newHotGoodsList();

    public ResultVO PizzaList();

    public GoodsVO selectByGoodsId(Integer id);

}
