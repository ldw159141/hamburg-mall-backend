package com.ldw.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HP刘德伟
 */
@Data
@NoArgsConstructor   //无参
@AllArgsConstructor  //传参
public class Result<T> {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(){
        return new Result(200,"操作成功",null);
    }
    public static Result success(Object data){
        return new Result(200,"操作成功",data);
    }
    public static Result error(String msg){
        return new Result(500,msg,null);
    }
    public static Result error(Integer code,String msg){
        return new Result(code,msg,null);
    }
    public static Result error(){
        return new Result(500,"系统错误,请联系管理员",null);
    }
}
