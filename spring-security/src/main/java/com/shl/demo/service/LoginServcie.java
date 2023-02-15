package com.shl.demo.service;

import com.shl.demo.domain.ResponseResult;
import com.shl.demo.domain.User;

public interface LoginServcie {

    ResponseResult login(User user);

    ResponseResult logout();
}