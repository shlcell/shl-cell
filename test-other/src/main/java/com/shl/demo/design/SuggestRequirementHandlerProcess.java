package com.shl.demo.design;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class SuggestRequirementHandlerProcess {

    public static void main(String[] args) {
        List<String> handler2 = getPropertyValue("handler2");
        System.out.println(handler2.size());

    }

    @Resource
    private HandleConfig handleConfig;

    @Value("#{'${suggest.requirement.handler1}'.split(',')}")
    private List<String> handlers;


    public static void process(UserInfo userInfo, List<String> suggestLists) throws Exception {

        // 如果想要实时的进行顺序的调整或者是增减。那必须要使用配置中心进行配置。
        // 比如springcloud里边自带的 git 的这种配置中心； applo 配置中心。

        getPropertyValue(userInfo.getUsername());

//        for (String handler : handlers) {
//        for (String handler : getPropertyValue(userInfo.getUsername())) {
//            AbstractSuggestRequirementHandler handle =
//                    (AbstractSuggestRequirementHandler) Class.forName(handler).newInstance();
//            handle.processHandler(userInfo, suggestLists);
//        }
    }

    /**
     * 反射获取指定类的属性值
     *
     * @param propertyName
     * @return
     */
    private static List<String> getPropertyValue(String propertyName) {
        Field field = ReflectionUtils.findField(HandleConfig.class, propertyName);
        if (field == null) {
            throw new IllegalArgumentException("No such field found: " + propertyName);
        }
        ReflectionUtils.makeAccessible(field);
        return (List<String>) ReflectionUtils.getField(field,Object.class);
    }

    /**
     * 打印属性名
     *
     * @param clazz
     * @return
     */
    public static String[] getPropertyNames(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String[] propertyNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            propertyNames[i] = fields[i].getName();
        }
        return propertyNames;
    }


}
