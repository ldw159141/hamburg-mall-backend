package com.ldw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldw.dto.TypeQuery;
import com.ldw.entity.Type;
import com.ldw.entity.User;
import com.ldw.mapper.TypeMapper;
import com.ldw.service.TypeService;
import com.ldw.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liudewei
 * @since 2023-03-28
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Override
    public PageVO page(TypeQuery typeQuery) {
        LambdaQueryWrapper<Type> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Type::getId);

        if (!"".equals(typeQuery.getName()) && typeQuery.getName()!=null){
            wrapper.like(Type::getName,typeQuery.getName());
        }

        Page<Type> page = typeMapper.selectPage(
                new Page<>(
                        typeQuery.getPageNum(),
                        typeQuery.getPageSize()
                ),wrapper
        );
        return new PageVO(page);
    }

}
