package com.ldw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author liudewei
 * @since 2023-04-21
 *
 * 有待优化这个订单太随意了
 */

@Data
  @EqualsAndHashCode(callSuper = false)
@ApiModel("订单实体类")
    public class Order implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty("订单ID")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 总价
     */
      @ApiModelProperty("总价")
      private String total;

      /**
     * 详细
     */
      @ApiModelProperty("详细")
      private String explan;

  @ApiModelProperty("用户id")
    private Integer userId;


}
