package com.shl.demo.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");                     //是否使用边框
        properties.setProperty("kaptcha.border.color", "105,179,90");       //边框颜色
        properties.setProperty("kaptcha.textproducer.font.color", "red");   //验证码字体颜色
        properties.setProperty("kaptcha.image.width", "105");               //图片宽度
        properties.setProperty("kaptcha.image.height", "45");               //图片高度
        properties.setProperty("kaptcha.textproducer.font.size", "35");     //字体大小
        properties.setProperty("kaptcha.session.key", "code");              //session key
        properties.setProperty("kaptcha.textproducer.char.length", "4");    //验证码长度
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");//字体

        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}

