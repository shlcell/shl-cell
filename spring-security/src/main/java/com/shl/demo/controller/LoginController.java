package com.shl.demo.controller;

import com.shl.demo.annoation.OpLog;
import com.shl.demo.domain.ResponseResult;
import com.shl.demo.domain.User;
import com.shl.demo.service.LoginServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginServcie loginServcie;

    @PostMapping("/user/login")
//    @OpLog(title = "用户登录")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginServcie.login(user);
    }

    @RequestMapping("/user/logout")
//    @OpLog(title = "用户退出登录")
    public ResponseResult logout(){
        return loginServcie.logout();
    }
}
