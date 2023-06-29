package com.ldw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldw.dto.RoleQuery;
import com.ldw.entity.Role;
import com.ldw.entity.Type;
import com.ldw.mapper.RoleMapper;
import com.ldw.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldw.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liudewei
 * @since 2023-04-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public Role selectByUserRoleId(Integer roleId) {
        return roleMapper.selectById(roleId);
    }

    @Override
    public PageVO page(RoleQuery roleQuery) {
        LambdaQueryWrapper<Role> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Role::getId);

        if (!"".equals(roleQuery.getName()) && roleQuery.getName()!=null){
            wrapper.like(Role::getName,roleQuery.getName());
        }

        Page<Role> page = roleMapper.selectPage(
                new Page<>(
                        roleQuery.getPageNum(),
                        roleQuery.getPageSize()
                ),wrapper
        );
        return new PageVO(page);
    }
}
