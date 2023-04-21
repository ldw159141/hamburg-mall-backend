package com.ldw.mapper;

import com.ldw.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liudewei
 * @since 2023-04-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
