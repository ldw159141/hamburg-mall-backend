package com.ldw.exception;



import com.ldw.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author HP刘德伟
 * 这里用Result类和方法，写的两个返回其实是一样的，只是练一下
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局运行时异常处理
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result<?> exceptionHandler(){
        return Result.error();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result<?> CustomException(CustomException e){
   log.error("错误原因为："+e.getMessage());
   return Result.error(e.getCode(),e.getMessage());
    }
}
