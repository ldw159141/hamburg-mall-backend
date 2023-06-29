package com.ldw.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
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
       * 允许传入空值
     * 类目id
     */


      @TableField(updateStrategy = FieldStrategy.IGNORED,insertStrategy = FieldStrategy.IGNORED)
      private Integer typeId;


}
