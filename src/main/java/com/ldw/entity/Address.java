package com.ldw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
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
 */
@ApiModel("地址实体类")
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Address implements Serializable {

    private static final long serialVersionUID=1L;

  @ApiModelProperty("地址ID")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 收货人
     */
      @ApiModelProperty("收货人")
    private String name;

      /**
     * 市
     */
      @ApiModelProperty("市")
      private String city;

      /**
     * 区
     */
      @ApiModelProperty("区")
      private String area;

      /**
     * 详细
     */
      @ApiModelProperty("详细")
      private String detailed;
  @ApiModelProperty("手机号")
    private String phone;
  @ApiModelProperty("用户id")
    private Integer userId;


}
