package com.shl.demo.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.Map;

/*
设计思路：
1确定要保存哪些信息，一般包括接口路径、请求方式、接口描述、请求参数、返回值等。
2设计一个API实体类，包括以上属性。
3从系统中扫描出所有的API接口路径，可以使用Spring提供的RequestMappingHandlerMapping类。
4将扫描得到的API信息保存到数据库中，可以使用JPA进行操作。
5在系统启动的时候调用API扫描方法执行扫描操作即可，如果需要定期更新API列表，可以使用定时任务或消息队列。

步骤：
1定义API实体类，包括接口路径、请求方式、接口描述、请求参数、返回值等属性，并添加相关注解。
2使用Spring提供的RequestMappingHandlerMapping类扫描所有Controller中@RequestMapping注解修饰的方法，将其对应的信息取出来，并存入API实体类中。
3使用JPA将API实体类保存到MySQL数据库中，可以使用JpaRepository进行操作。
4在系统启动时，调用API扫描方法执行扫描操作，并将扫描得到的API信息保存到数据库中。
5如果需要定期更新API列表，可以使用定时任务或消息队列，周期性地重新扫描系统API并更新数据库中的信息。
*/

/**
 * 以上代码会遍历所有被@Controller注解标记的Bean，获取其中所有被@RequestMapping注解标记的方法。
 * 然后，将每个方法与其对应的请求路径和请求方式封装成RequestMappingInfo对象，
 * 调用RequestMappingHandlerMapping的registerMapping方法注册到其中即可完成映射。
 */
public class MyRequestMappingHandler {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;
    @Autowired
    private ApplicationContext applicationContext;

    public void init() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Controller.class);
        for (Object bean : beans.values()) {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                if (requestMapping != null) {
                    String[] paths = requestMapping.value();
                    RequestMethod[] httpMethods = requestMapping.method();
                    for (String path : paths) {
                        for (RequestMethod httpMethod : httpMethods) {
                            RequestMappingInfo mappingInfo = RequestMappingInfo.paths(path).methods(httpMethod).build();
                            handlerMapping.registerMapping(mappingInfo, bean, method);
                        }
                    }
                }
            }
        }
    }

}
