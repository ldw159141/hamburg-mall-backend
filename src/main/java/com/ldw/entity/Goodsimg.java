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
 * @since 2023-03-29
 */

@Data
  @EqualsAndHashCode(callSuper = false)
@ApiModel("图片实体类")
    public class Goodsimg implements Serializable {

    private static final long serialVersionUID=1L;
      @ApiModelProperty("图片id")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 图片名字、地址
     */
      @ApiModelProperty("图片名")
      private String url;
      @ApiModelProperty("商品id")
      private Integer goodsId;


}
