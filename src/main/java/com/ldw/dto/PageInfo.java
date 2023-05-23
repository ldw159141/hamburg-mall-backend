package com.ldw.dto;

import lombok.Data;

/**
 * @author HP刘德伟
 */
@Data
public class PageInfo {

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页显示数量
     */
    private Integer pageSize;
}
