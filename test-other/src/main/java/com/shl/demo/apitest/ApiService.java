package com.shl.demo.apitest;

import com.shl.demo.apitest.ApiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Map;

/*获取Spring容器中所有被@RestController或@Controller注解标记的Bean，
并分别获取其中被@RequestMapping标记的类和方法，进而获取到接口的url和请求方式等信息。
之后，我们使用JdbcTemplate来操作数据库，将所有接口信息保存到MySQL数据库中。
使用时，只需要调用ApiService.saveAllApiInfos()方法即可*/
@Service
public class ApiService {

//    @Autowired
//    private DataSource dataSource;
    @Autowired
    private ApplicationContext applicationContext;

    public void saveAllApiInfos() {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RestController.class);
        beans.putAll(applicationContext.getBeansWithAnnotation(Controller.class));

        for (Object bean : beans.values()) {
            Class<?> clazz = bean.getClass();
            RequestMapping requestMapping = AnnotationUtils.findAnnotation(clazz, RequestMapping.class);
            if (requestMapping != null) {
                String[] basePaths = requestMapping.value();
                RequestMethod[] baseMethods = requestMapping.method();
                for (String basePath : basePaths) {
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        RequestMapping methodRequestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                        if (methodRequestMapping != null) {
                            String[] paths = methodRequestMapping.value();
                            RequestMethod[] methods2 = methodRequestMapping.method();
                            for (String path : paths) {
                                for (RequestMethod httpMethod : methods2) {
                                    String url = basePath + path;
                                    ApiEntity api = new ApiEntity();
                                    api.setMethod(httpMethod.name());
                                    api.setUrl(url);
//                                    jdbcTemplate.update("INSERT INTO api_info (url, method) VALUES (?, ?)", url, httpMethod.name());
                                }
                            }
                        } else {
                            GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
                            if (getMapping != null) {
                                String[] paths = getMapping.value();
                                for (String path : paths) {
                                    String url = basePath + path;
                                    ApiEntity api = new ApiEntity();
                                    api.setMethod(RequestMethod.GET.name());
                                    api.setUrl(url);
//                                    jdbcTemplate.update("INSERT INTO api_info (url, method) VALUES (?, ?)", url, RequestMethod.GET.name());
                                }
                            } else {
                                PostMapping postMapping = AnnotationUtils.findAnnotation(method, PostMapping.class);
                                if (postMapping != null) {
                                    String[] paths = postMapping.value();
                                    for (String path : paths) {
                                        String url = basePath + path;
                                        ApiEntity api = new ApiEntity();
                                        api.setMethod(RequestMethod.POST.name());
                                        api.setUrl(url);
//                                        jdbcTemplate.update("INSERT INTO api_info (url, method) VALUES (?, ?)", url, RequestMethod.POST.name());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

