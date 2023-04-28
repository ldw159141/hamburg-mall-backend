package com.ldw.service;

import com.ldw.common.Result;
import com.ldw.dto.UserLoginDTO;
import com.ldw.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldw.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liudewei
 * @since 2023-04-21
 */
public interface UserService extends IService<User> {

    public ResultVO login(UserLoginDTO user);
}
