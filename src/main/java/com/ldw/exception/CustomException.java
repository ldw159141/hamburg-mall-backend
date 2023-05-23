package com.ldw.exception;

import lombok.Getter;

/**
 * @author HP刘德伟
 */
@Getter
public class CustomException extends RuntimeException{
    private Integer code;

    public CustomException(Integer code,String msg){
    super(msg);//给父级传参数
    this.code=code;
    }

    public CustomException(String msg){
        super(msg);
        this.code=500;
    }
}
