package com.shl.demo.apitest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * 在Bean初始化前或初始化后执行一些特定的操作。
 * 在Spring框架中，可以通过实现ApplicationContextAware接口来获取ApplicationContext对象，从而手动获取所需的bean。
 *
 * 值得注意的是，这种手动获取bean的方式不是很推荐，因为ApplicationContext对象的获取会比较耗时。
 * 在实际开发中，应该尽量使用Spring框架提供的依赖注入功能，让Spring框架自动完成Bean的装配和管理。只有在某些特殊场景下才需要手动获取bean
 */
@Component
public class SpringApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

}
