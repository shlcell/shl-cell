package com.shl.demo.design;

import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

@RestController
@RequestMapping("/shl/handler")
public class HandlerController {

    @Resource
    private HandleConfig handleConfig;

    @PostMapping("/test")
    public void test() {
        String name = "handler1";
        List<String> handler1 = handleConfig.getHandler1();
        System.out.println(handler1.size());
        List<String> handler11 = getPropertyValue(handleConfig, name);
        System.out.println(handler11.size());
    }

    private List<String> getPropertyValue(HandleConfig handleConfig, String propertyName) {
        Field field = ReflectionUtils.findField(HandleConfig.class, propertyName);
        if (field == null) {
            throw new IllegalArgumentException("No such field found: " + propertyName);
        }
        ReflectionUtils.makeAccessible(field);
        return (List<String>) ReflectionUtils.getField(field, handleConfig);
    }
}
