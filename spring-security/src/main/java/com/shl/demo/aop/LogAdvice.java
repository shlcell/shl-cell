package com.shl.demo.aop;

import com.alibaba.fastjson.JSON;
import com.shl.demo.domain.LoginUser;
import com.shl.demo.domain.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
//告诉spring，这个一个切面类，里面可以定义切入点和通知
@Aspect
@Slf4j
public class LogAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    //统计请求的处理时间
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    //切入点表达式,也可以直接在通知上编写切入点表达式
    @Pointcut("@annotation(com.shl.demo.annoation.OpLog)")
    public void aspect() {
    }

    @Around("aspect()")
    public ResponseResult around(JoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget().getClass().getName();
        //通过joinPoint获取参数
        Object[] args = joinPoint.getArgs();
        log.info("调用者:{} 调用方法:{} 调用参数:{}", target, joinPoint.getSignature(), args);
        long start = System.currentTimeMillis();
        log.info("===========环绕通知 环绕前========");
        //执行连接点的方法
        try {
            Object proceed = ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            log.info("===========环绕通知 环绕后========");
            log.info("调用方法总耗时 time = " + (end - start) + " ms");
            return (ResponseResult) proceed;
        } catch (Throwable throwable) {
            long end = System.currentTimeMillis();
            log.info("===========环绕通知 环绕后========");
            log.info("调用方法总耗时 time = " + (end - start) + " ms");
            return new ResponseResult(500,"系统错误");
        }
    }

    @Before("aspect()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求的内容
        logger.info("请求URL:" + request.getRequestURL().toString());
        logger.info("请求METHOD:" + request.getMethod());
    }

    @AfterReturning(returning = "ret", pointcut = "aspect()")
    public void doAfterReturning(Object ret) {
//        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long id = loginUser.getId();
//        String userName = loginUser.getUsername();
        //处理完请求后，返回内容
        logger.info("方法返回值:" + JSON.toJSONString(ret));
        logger.info("方法执行时间:" + (System.currentTimeMillis() - startTime.get()));
    }

    @AfterThrowing(pointcut = "aspect()")
    public void doAfterThrowing(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("接口访问失败，URI:[{}], 耗费时间:[{}] ms", request.getRequestURI(), System.currentTimeMillis() - startTime.get());
    }
}

