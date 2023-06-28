package com.ldw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldw.entity.Role;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liudewei
 * @since 2023-04-21
 */
public interface RoleService extends IService<Role> {

    Role selectByUserRoleId(Integer roleId);
}
