package com.shl.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shl.demo.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    void saveOrUpdate(@Param("user") User user);

    int saveOrUpdateBatch(@Param("userList")List<User> userList);
}