package com.ldw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
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
    public class Address implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 收货人
     */

    private String name;

      /**
     * 市
     */
      private String provincial;

      /**
     * 市
     */
      private String city;

      /**
     * 区
     */
      private String area;

      /**
     * 详细
     */
      private String detailed;

    private String phone;

    private Integer userId;


}
