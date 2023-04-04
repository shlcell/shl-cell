package com.shl.demo.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
CommandLineRunner是Spring Boot框架中的一个接口，它的作用是在Spring应用程序启动后自动执行一些代码逻辑。

在Spring Boot中，我们可以通过实现CommandLineRunner接口或者使用@Component注解的方式来定义应用程序的启动行为。
当应用程序启动时，所有实现了CommandLineRunner接口或者被@Component注解标记的类都会被自动加载，并且run()方法会自动执行。
run()方法可以接受一组命令行参数，并在整个应用程序启动之前完成必要的初始化工作，例如数据库连接等。

CommandLineRunner是Spring Boot框架提供的一种方便的应用程序启动回调机制，可以让我们在应用程序启动后完成一些必要的初始化工作。
*/
@Component
public class MyRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // 这里编写应用程序启动后需要执行的代码逻辑
    }
}

