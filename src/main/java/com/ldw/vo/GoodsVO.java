package com.ldw.vo;

import com.ldw.entity.Goodsimg;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author HP刘德伟
 */
@Data
public class GoodsVO {
    private Integer id;

    /**
     * 编码
     */
    private String coder;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 促销价格
     */

    private BigDecimal promoPrice;

    /**
     * 库存
     */
    private String stock;

    /**
     * 拼接地址、图片名
     */
    private String url;

    /**
     * 类目id
     */
    private Integer typeId;

    private List<Goodsimg> goodsimg;



}
