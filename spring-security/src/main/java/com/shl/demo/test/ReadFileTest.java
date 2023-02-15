package com.shl.demo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFileTest {

    private static final Logger log = LoggerFactory.getLogger(ReadFileTest.class);

    private static final String LOG_POSITION = "D:\\home\\ruoyi\\logs";

    public static void main(String[] args) {
        // 读取指定文件夹下的log文件名称
        List<String> logName = getLogName(LOG_POSITION);
        for (String name : logName) {
            System.out.println(name);
        }
        System.out.println("========================================");
        // 读取指定文件夹下的log文件
        String filePath = LOG_POSITION + "\\" + "sys-info.2022-09-09.log";
        System.out.println(readTxt(filePath));
    }

    public static List<String> getLogName(String folder) {
        List<String> fileNameList = new ArrayList<>();
        File file = new File(folder);
        File[] files = file.listFiles();
        if (files != null) {
            for (File fileName : files) {
                String name = fileName.getName();
                fileNameList.add(name);
            }
        }
        return fileNameList;
    }


    public static String readTxt(String filePath) {
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null;
                StringBuilder logInfo = new StringBuilder();
                while ((lineTxt = br.readLine()) != null) {
                    logInfo.append(lineTxt + "\n");
                }
                br.close();
                return logInfo.toString();
            } else {
                System.out.println("文件不存在!");
            }
        } catch (Exception e) {
            System.out.println("文件读取错误!");
        }
        return "读取文件失败";
    }
}