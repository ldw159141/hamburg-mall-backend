package com.ldw.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ldw.common.Result;
import com.ldw.entity.Goods;
import com.ldw.service.GoodsService;
import com.ldw.service.GoodsimgService;
import com.ldw.util.RedisUtil;
import com.ldw.util.ResultVOUtil;
import com.ldw.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Api(value = "测试模块",tags = "测试模块")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsimgService goodsimgService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

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
     * 功能未使用..
     * */
    @GetMapping("/selectById/{id}")
    public ResultVO selectById(@PathVariable("id") Integer id){
        return ResultVOUtil.success(this.goodsService.getById(id));
    }
    /**
     * 测试功能successSetMsg和查询热卖商品mapper
     * 功能未使用
     * */
    @GetMapping("/test")
    public ResultVO test(){
        ResultVO resultVO=new ResultVO<>();
        resultVO.setMsg("成功了");
        resultVO.setCode(200);
        resultVO.setData(this.goodsimgService.HotGoodsList());
        return resultVO;
    }
    /**
     * 测试功能successSetMsg和查询热卖商品mapper2
     * 功能未使用
     * */
    @GetMapping("/test2")
    public ResultVO test2(){
     return ResultVOUtil.successSetMsg(this.goodsimgService.HotGoodsList(),"成功返回数据",200);
    }




    /**
    查新热卖商品信息带图片列表，返回给后台
     这里写的不好，serviceImpl已经返回了一层Result，这里再套一层，会有2个msg和code
    */
    @GetMapping("/hotGoodsList")
    public ResultVO hotGoodsList() {
      //  Object o = redisTemplate.opsForValue().get("hotGoodsList");
        Object o=redisUtil.get("hotGoodsList");
        if (o != null) {
            return ResultVOUtil.success(o);
        } else {
            return ResultVOUtil.success(this.goodsimgService.HotGoodsList());
        }
    }

    /**
     * 新热卖商品， 解决前端需要数据处理问题,返回给前台
     * @return
     */
    @GetMapping("/newHotGoodsList")
    public ResultVO newHotGoodsList(){
        //这里报错，明明已经写入缓存但是没办法正常读取出来
        //报错原因是取值的时候没正确的序列化
        //是序列化Jackson2JsonRedisSerializer 的问题，应改成GenericJackson2JsonRedisSerializer
      //  redisTemplate.setValueSerializer(new StringRedisSerializer());,会导致全部失效
     //   Object o = redisTemplate.opsForValue().get("NewHotGoodsList");
//        Object o=redisUtil.get("NewHotGoodsList");
//       if (o != null) {
//           return ResultVOUtil.success(o);
//       } else {
            return ResultVOUtil.success(this.goodsimgService.newHotGoodsList());}
//    }
    /**
     查新商品信息带图片列表
     */
    @GetMapping("/goodsList")
    public ResultVO goodList(){

       // Object o = redisTemplate.opsForValue().get("GoodsList");
        Object o=redisUtil.get("GoodsList");
        if (o != null) {
            return ResultVOUtil.success(o);
        } else {
            return ResultVOUtil.success(this.goodsimgService.GoodsList());
        }
    }

    /**
     查新轮播图片列表
     */

    @GetMapping("/findSlide")
    public ResultVO findSlide() {
       // Object o = redisTemplate.opsForValue().get("Slide");
        Object o=redisUtil.get("Slide");
        if (o != null) {
            return ResultVOUtil.success(o);
        } else {
            List<Goods> slideList = this.goodsService.list(new QueryWrapper<Goods>().eq("type_id", 2));
            ResultVO resultVO=new ResultVO<>();
            resultVO.setData(slideList);
            redisTemplate.opsForValue().set("Slide",resultVO);
            return ResultVOUtil.success(slideList);
        }
    }



}








