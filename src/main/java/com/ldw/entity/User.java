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
 */

@Data
  @EqualsAndHashCode(callSuper = false)
@ApiModel("用户实体类")
    public class User implements Serializable {

    private static final long serialVersionUID=1L;
      @ApiModelProperty("用户id")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;
  @ApiModelProperty("账号")
    private String username;
  @ApiModelProperty("密码")
    private String password;
  @ApiModelProperty("权限ID")
    private Integer roleId;
  @ApiModelProperty("名字")
  private String name;
  @ApiModelProperty("店铺")
  private String store;
  @ApiModelProperty("地址")
  private String address;
  @ApiModelProperty("手机")
  private String phone;

}
