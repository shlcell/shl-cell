package com.shl.demo.sqzxbg;

import com.alibaba.fastjson2.JSON;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Data
//@ToString(callSuper = true) // tostring 输出父类属性
public class Sqzxbg1005Dto extends LocalResultDto {

    private String ljkpje;

    private Integer ddbs;


    public static void main(String[] args) {
//        Sqzxbg1005Dto sqzxbg1005Dto = new Sqzxbg1005Dto();
//        sqzxbg1005Dto.setGmv("3");
//        sqzxbg1005Dto.setIfValid("1");
//        sqzxbg1005Dto.setLjkpje("sdfdsf");
//        sqzxbg1005Dto.setDdbs(1232);
//        Integer ddbs = sqzxbg1005Dto.getDdbs();
//        if (1231 != ddbs) {
//            System.out.println("1");
//        }
//        String s = new GsonBuilder().disableHtmlEscaping().create().toJson(sqzxbg1005Dto);
//        System.out.println(s);
//        System.out.println(sqzxbg1005Dto);
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//
//        // 获取当前日期和时间
//        Date date = new Date();
//
//        // 将 Date 对象格式化为字符串
//        String formattedDate = df.format(date);
//
//        // 输出结果
//        System.out.println(formattedDate);
//        System.out.println(date);
//        System.out.println(date.getTime());


        List<String> time = getMonths("yyyy-MM", "3");
        String endTime1 = time.get(0);
        String startTime1 = time.get(time.size() - 1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDate now = LocalDate.now();
        String endTime = now.format(dtf);
        LocalDate startDate = now.plusMonths(-3);
        String startTime = startDate.format(dtf);
        System.out.println(endTime1);
        System.out.println(startTime1);
        System.out.println(endTime);
        System.out.println(startTime);

        StandardVo standardVo = new StandardVo();
        standardVo.setInterfaceCode("2432432");
        standardVo.setPtsbm("dsfdsfds");
        standardVo.setNsrsbh("dsfdsfds");
        standardVo.setCxyfs("dsfdsfds");
        standardVo.setDsfcjbm("dsfdsfds");
        standardVo.setByzd1("dsfdsfds");

        REQUEST_Xxlshz request_xxlshz = new GsonBuilder().disableHtmlEscaping().create().fromJson(JSON.toJSONString(standardVo), REQUEST_Xxlshz.class);
        REQUEST_Xxlshz request_xxlshz1 = new GsonBuilder().disableHtmlEscaping().create().fromJson(standardVo.toString(), REQUEST_Xxlshz.class);
        System.out.println(request_xxlshz.toString());
        System.out.println(request_xxlshz1.toString());
    }
//    public static void main(String[] args) {
//        Random random = new Random();
//        int randomNumber = 100000 + random.nextInt(900000);
//        String s = "ZDYT8888" + "-" + "02" + "-" + System.currentTimeMillis() + "-" + "91350100MA344D1W14" + "-" + randomNumber;
//        System.out.println(s);
//        System.out.println(s.length());
//    }

    /**
     * 得到除了本月，过去的X个月固定时间格式的月份集合
     * @param
     * @return
     */
    public static List<String> getMonths(String date_format,String cxyfs) {
        List<String> months = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(date_format);
        LocalDate today = LocalDate.now();
        months.add(today.format(dtf));
        for (int i = 1; i <= Integer.parseInt(cxyfs); i++) {
            LocalDate localDate = today.minusMonths(i);
            String format = localDate.format(dtf);
            months.add(format);
        }
        months.remove(0);
        return months;
    }
}
