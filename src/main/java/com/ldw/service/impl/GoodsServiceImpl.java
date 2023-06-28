package com.ldw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldw.dto.GoodsQuery;
import com.ldw.entity.Goods;
import com.ldw.entity.User;
import com.ldw.mapper.GoodsMapper;
import com.ldw.service.GoodsService;
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
 * @since 2023-03-28
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageVO page(GoodsQuery goodsQuery) {
        LambdaQueryWrapper<Goods> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Goods::getId);

        if (!"".equals(goodsQuery.getTitle()) && goodsQuery.getTitle()!=null){
            wrapper.like(Goods::getTitle,goodsQuery.getTitle());
        }

        Page<Goods> page = goodsMapper.selectPage(
                new Page<>(
                        goodsQuery.getPageNum(),
                        goodsQuery.getPageSize()
                ),wrapper
        );
        return new PageVO(page);
    }
}
