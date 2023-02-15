package com.shl.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shl.demo.domain.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);
}