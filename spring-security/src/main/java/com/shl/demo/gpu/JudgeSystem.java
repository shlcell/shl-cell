package com.shl.demo.gpu;

import org.springframework.stereotype.Component;


@Component
public class JudgeSystem {

    public static boolean isWindows() {
        System.out.println(System.getProperty("os.name").toLowerCase());
        boolean windows = System.getProperty("os.name").toLowerCase().contains("windows");
        return windows;
    }

    public static boolean isLinux() {
        boolean linux = System.getProperty("os.name").toLowerCase().contains("linux");
        return linux;
    }
}


