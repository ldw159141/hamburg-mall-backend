package com.ldw.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author liudewei
 * @since 2023-03-28
 */
@Data
  @EqualsAndHashCode(callSuper = false)
@ApiModel("商品实体类")
    public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty("商品ID")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 编码
     */
      @ApiModelProperty("编码")
      private String coder;

      /**
     * 标题
     */
      @ApiModelProperty("标题")
      private String title;

      /**
     * 描述
     */
      @ApiModelProperty("描述")
      private String description;

      /**
     * 价格
     */
      @ApiModelProperty("价格")
      private BigDecimal price;

      /**
     * 促销价格 
     */

      @ApiModelProperty("促销价格")
    private BigDecimal promoPrice;

      /**
     * 库存
     */
      @ApiModelProperty("库存")
      private String stock;

      /**
     * 拼接地址、图片名
     */
      @ApiModelProperty("图片名")
      private String url;

      /**
       * 允许传入空值
     * 类目id
     */

      @ApiModelProperty("类目id")
      @TableField(updateStrategy = FieldStrategy.IGNORED,insertStrategy = FieldStrategy.IGNORED)
      private Integer typeId;


}
