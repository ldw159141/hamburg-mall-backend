package com.ldw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ldw.entity.Goods;
import com.ldw.entity.Goodsimg;
import com.ldw.mapper.GoodsMapper;
import com.ldw.mapper.GoodsimgMapper;
import com.ldw.service.GoodsimgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldw.vo.GoodsVO;
import com.ldw.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liudewei
 * @since 2023-03-29
 */
@Service
public class GoodsimgServiceImpl extends ServiceImpl<GoodsimgMapper, Goodsimg> implements GoodsimgService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsimgMapper goodsimgMapper;

    /**
     * 热卖商品图
      * @return
     */
    @Override
    public ResultVO HotGoodsList() {
        List<Goods> goodsList = this.goodsMapper.selectList(new QueryWrapper<Goods>().eq("type_id", 1));
        List<GoodsVO> goodsVOList =new ArrayList<>();
        for (Goods goods:goodsList){
            GoodsVO goodsVO =new GoodsVO();
            BeanUtils.copyProperties(goods, goodsVO);
      //     goodsVO.setGoodsimg(this.goodsimgMapper.findUrl(goods.getId()));
            

           goodsVO.setGoodsimg(this.goodsimgMapper.selectList(new QueryWrapper<Goodsimg>().eq("goods_id",goods.getId())));
            goodsVOList.add(goodsVO);
        }
        ResultVO resultVO=new ResultVO<>();
        resultVO.setData(goodsVOList);

        return resultVO;
    }

    /**
     * 商品图
     * @return
     */

    @Override
    public ResultVO GoodsList() {
        List<Goods> goodsList = this.goodsMapper.selectList(new QueryWrapper<Goods>().eq("type_id", 3));
        List<GoodsVO> goodsVOList =new ArrayList<>();
        for (Goods goods:goodsList){
            GoodsVO goodsVO =new GoodsVO();
            BeanUtils.copyProperties(goods, goodsVO);
//            hotGoodsVO.setGoodsimg(this.goodsimgMapper.findUrl(goods.getId()));
            goodsVO.setGoodsimg(this.goodsimgMapper.selectList(new QueryWrapper<Goodsimg>().eq("goods_id",goods.getId())));
            goodsVOList.add(goodsVO);
        }
        ResultVO resultVO=new ResultVO<>();
        resultVO.setData(goodsVOList);

        return resultVO;
    }

    /**
     * 将Goodsimg对象中的url循环放入列表中
     * @return
     */
    @Override
    public ResultVO newGoodsList() {
        List<Goods> goodsList = this.goodsMapper.selectList(new QueryWrapper<Goods>().eq("type_id", 3));
        List<GoodsVO> goodsVOList =new ArrayList<>();
        for (Goods goods:goodsList){
            GoodsVO goodsVO =new GoodsVO();
            BeanUtils.copyProperties(goods, goodsVO);

          List<Goodsimg> list= this.goodsimgMapper.selectList(new QueryWrapper<Goodsimg>().eq("goods_id",goods.getId()));

           goodsVO.setGoodsimg(list);
            goodsVOList.add(goodsVO);
        }
        ResultVO resultVO=new ResultVO<>();
        resultVO.setData(goodsVOList);

        return resultVO;
    }

}
