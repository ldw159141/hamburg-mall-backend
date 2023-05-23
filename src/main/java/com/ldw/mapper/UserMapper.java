package com.ldw.mapper;

import com.ldw.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liudewei
 * @since 2023-04-21
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void insertUser(User user);
}
