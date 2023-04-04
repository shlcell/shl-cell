package com.shl.demo.apitest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 要让SaveAllApiInfoStartupRunner类在Spring容器启动时运行，需要使用Spring的CommandLineRunner接口，并将其加上@Component注解。
 * 由于在启动时还没有初始化完所有的Bean，需要手动获取ApplicationContext对象，
 * 通过这个对象来获取RequestMappingHandlerMapping类实例，进而获取系统中的所有API接口信息。
 */
@Component
public class SaveAllApiInfoStartupRunner implements CommandLineRunner {

    @Autowired
    private ApiInfoDao apiInfoDao;
//    @Autowired
//    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        ApplicationContext applicationContext = SpringApplicationContextUtil.getApplicationContext();
        RequestMappingHandlerMapping mappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mappingHandlerMapping.getHandlerMethods();

        List<ApiEntity> apiList = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            RequestMappingInfo mappingInfo = entry.getKey();
            HandlerMethod method = entry.getValue();

            Set<String> patterns = mappingInfo.getPatternsCondition().getPatterns();
            RequestMethod requestMethod = mappingInfo.getMethodsCondition().getMethods().iterator().next();

            for (String pattern : patterns) {
                ApiEntity api = new ApiEntity();
                api.setUrl(pattern);
                api.setMethod(requestMethod.name());
                apiList.add(api);
            }
        }

        for (ApiEntity api : apiList) {
            apiInfoDao.save(api);
        }
    }
}

