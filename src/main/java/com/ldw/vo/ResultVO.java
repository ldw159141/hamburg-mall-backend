package com.ldw.vo;

import lombok.Data;

/**
 * @author HP刘德伟
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private T data;
    private String msg;
}
