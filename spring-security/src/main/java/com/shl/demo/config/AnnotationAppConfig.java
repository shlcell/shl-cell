package com.shl.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//开启了spring对aspect的支持
@EnableAspectJAutoProxy
@Configuration
@ComponentScan("com.shl.demo")
public class AnnotationAppConfig {

}