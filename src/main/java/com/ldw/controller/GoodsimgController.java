package com.ldw.controller;


import com.ldw.common.Result;
import com.ldw.entity.Goodsimg;
import com.ldw.service.GoodsimgService;
import com.ldw.util.ResultVOUtil;
import com.ldw.vo.ResultVO;
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
 * @since 2023-03-29
 */
@RestController
@RequestMapping("/goodsimg")
public class GoodsimgController {

    @Autowired
    private GoodsimgService goodsimgService;

  @GetMapping("/selectByGoodsId/{id}")
    public Result selectByGoodsId(@PathVariable("id") Integer id){

      return Result.success(goodsimgService.selectByGoodsId(id));
  }


}

