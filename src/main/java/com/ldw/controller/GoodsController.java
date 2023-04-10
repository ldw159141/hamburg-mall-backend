package com.ldw.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ldw.entity.Goods;
import com.ldw.service.GoodsService;
import com.ldw.service.GoodsimgService;
import com.ldw.util.ResultVOUtil;
import com.ldw.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liudewei
 * @since 2023-03-28
 */
@Api(value = "测试模块",tags = "测试模块")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsimgService goodsimgService;


    /**
     * 查找热门商品信息列表，不带图片列表
     * 功能未使用
     * */
    @GetMapping("/findGoods")
    public ResultVO findGoods(){
        List<Goods> goodsList = this.goodsService.list(new QueryWrapper<Goods>().eq("type_id", 1));

        return ResultVOUtil.success(goodsList);


//        List<Goods> goodsList=this.goodsService.list();
//        return ResultVOUtil.success(goodsList);

//        return ResultVOUtil.success(this.goodsService.list());
    }

    /**
     * 根据id查找热门商品信息列表，不带图片列表
     * 功能未使用
     * */
    @GetMapping("/selectById/{id}")
    public ResultVO selectById(@PathVariable("id") Integer id){
        return ResultVOUtil.success(this.goodsService.getById(id));
    }



    /**
    查新热卖商品信息带图片列表
    */
    @GetMapping("/hotGoodsList")
    public ResultVO list(){

        return ResultVOUtil.success(this.goodsimgService.HotGoodsList());
    }

    /**
     查新商品信息带图片列表
     */
    @GetMapping("/goodsList")
    public ResultVO goodList(){

        return ResultVOUtil.success(this.goodsimgService.GoodsList());
    }

    /**
     查新轮播图片列表
     */

    @GetMapping("/findSlide")
    public ResultVO findSlide() {
        List<Goods> slideList = this.goodsService.list(new QueryWrapper<Goods>().eq("type_id", 2));

        return ResultVOUtil.success(slideList);
    }
}

