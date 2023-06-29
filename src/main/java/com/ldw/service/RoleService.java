package com.ldw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldw.dto.RoleQuery;
import com.ldw.dto.TypeQuery;
import com.ldw.entity.Role;
import com.ldw.vo.PageVO;

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


    public PageVO page(RoleQuery roleQuery);
}
