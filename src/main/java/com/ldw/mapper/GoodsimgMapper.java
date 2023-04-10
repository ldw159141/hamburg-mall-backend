package com.ldw.mapper;

import com.ldw.entity.Goodsimg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldw.vo.ResultVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liudewei
 * @since 2023-03-29
 */
@Mapper
public interface GoodsimgMapper extends BaseMapper<Goodsimg> {

    public List findUrl(Integer id);
}
