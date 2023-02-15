package com.shl.demo.handle;

import com.alibaba.fastjson.JSON;
import com.shl.demo.domain.ResponseResult;
import com.shl.demo.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理类
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), authException.getMessage());
        ResponseResult result = new ResponseResult();
        if (authException.getMessage().equals("用户名或密码错误")) {
            result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "用户名或密码错误");
        } else {
            result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "认证失败");
        }
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
