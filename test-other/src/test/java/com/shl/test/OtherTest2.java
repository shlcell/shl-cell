package com.shl.test;

import org.junit.Test;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class OtherTest2 {

    @Test
    public void json() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("2", 312321);
        jsonObject.put("1", 312321);
        String s = JSON.toJSONString(jsonObject);
//        System.out.println(s);

        HashMap<Object, Object> map = new HashMap<>();
        HashMap<Object, Object> map1 = new HashMap<>();
        map.put("3", 3);
        map1.put("4", jsonObject);
        map.put("date", map1);
        Object o = (Object) map;
        JSONObject jsonObject1 = new JSONObject((Map) o);
        jsonObject1.put("5", 5);
        String s1 = JSON.toJSONString(jsonObject1);
        System.out.println(s1);
        String date = jsonObject1.getString("date");
        System.out.println(date);
    }

    // 创建一个Integer类型的线程局部变量
    private static final ThreadLocal<Integer> threadLocalValue = new ThreadLocal<>();

    @Test
    public void ThreadLocal() {
        // 创建两个线程，分别向ThreadLocal变量中存入不同的值
        Thread threadOne = new Thread(() -> {
            threadLocalValue.set(1);
            print("ThreadOne");
            threadLocalValue.remove(); // 移除当前线程的ThreadLocal值，避免内存泄漏
        });

        Thread threadTwo = new Thread(() -> {
            threadLocalValue.set(2);
            print("ThreadTwo");
            threadLocalValue.remove(); // 移除当前线程的ThreadLocal值，避免内存泄漏
        });

        // 启动线程
        threadOne.start();
        threadTwo.start();
    }

    private static void print(String threadName) {
        // 在当前线程中获取ThreadLocal中的值
        Integer value = threadLocalValue.get();
        System.out.println(threadName + ": " + value);
    }

    public int powerOfThree(int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= 3;
        }
        return result;
    }

    @Test
    public void dss() {
        JSONObject jsonObject = new JSONObject();
        List<Integer> list = new ArrayList<>();
        list.add(130000);
        list.add(140000);
        jsonObject.put("areaInfo", list);
        List<String> areaInfo = jsonObject.getList("areaInfo", String.class);

        boolean contains = list.contains("130000");
        boolean contains1 = areaInfo.contains("130000");
        System.out.println(contains);
        System.out.println(contains1);

    }

    @Test
    public void str() {
        String s = "fds.fds.fsd";
        s = s.replaceAll("\\.", "-");
        System.out.println(s);
    }

    @Test
    public void str2() {
        List<Double> seriesData = new ArrayList<>();
        BigDecimal result = new BigDecimal("2815061.00");
        BigDecimal divideNum = new BigDecimal(100000000);
        seriesData.add(result.divide(divideNum, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println(seriesData.get(0));
    }

    @Test
    public void str1() {
        List<String> monthList = this.getLastMonth("202312", 11);
        System.out.println(monthList);
    }

    /**
     * 获取指定数量的过去月份，包含当月
     *
     * @return
     */
    public static List<String> getLastMonth(String dateStr, int count) {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = count - 2; i >= -1; i--) {
            int nowYear = calendar.get(Calendar.YEAR);
            int nowmonth = calendar.get(Calendar.MONTH) - i;
            String date = "";
            if (nowmonth >= 1) {
                date = nowYear + (nowmonth >= 10 ? "" : "0") + nowmonth;
            } else {
                int p = 11 - i;//剩余循环次数
                int oldYear = calendar.get(Calendar.YEAR) - 1;
                int oldMonth = calendar.get(Calendar.MONTH) + 1 + p;
                date = oldYear + (oldMonth >= 10 ? "" : "0") + oldMonth;
            }
            list.add(date);
        }
        return list;
    }


    @Test
    public void baffle() {
//        Map<String, String> baffle = new HashMap<>();
//        baffle.put("key1", null);
//        baffle.put("key2", "2");
//        baffle.put("key3", "1");
//        long count = baffle.values().stream()
//                .filter(value -> value == null || !value.equals("1"))
//                .count();
//        System.out.println(count);
        List<Integer> years = new ArrayList<>();
        int currentYear = LocalDate.now().getYear() - 2;
        for (int i = 0; i < 3; i++) {
            years.add(currentYear + i);
        }
        System.out.println(years);

    }

//    public static String extractDomain (String url){
//        if (StringUtils.isBlank(url)) {
//            return "";
//        }
//        int index = 0;
//        if (url.startsWith(HTTP)) {
//            index = HTTP.length();
//        } else if (url.startsWith(HTTPS)) {
//            index = HTTPS.length();
//        } else {
//            return "";
//        }
//        String safeUrl = url.substring(index);
//        index = safeUrl.indexOf('/');
//        if (index > 0) {
//            safeUrl = safeUrl.substring(0, index);
//        }
//        String[] array = safeUrl.split("\.");
//        if (array.length < 2) {
//            return "";
//        }
//        String part1 = array[array.length - 2];
//        String part2 = array[array.length - 1];
//        if (StringUtils.isNotBlank(part1) && StringUtils.isNotBlank(part2)) {
//            if (!isIn(part2, DOMAINS)) {
//                return "";
//            }
//            return part1 + '.' + part2;
//        }
//        return "";
//    }

    @Test
    public void str3() {
        String s = this.subAreaCode("100000");
        System.out.println(s);
    }


    //获取所在地去掉0
    public String subAreaCode(String districtCode) {

        if (districtCode != null) {
            int areacode = Integer.valueOf(districtCode);
            if (areacode % 100000 == 0) {
                //为全国时
                areacode = areacode / 100000;
            } else if (areacode % 10000 == 0) {
                //入参为市级调用区县统计
                areacode = areacode / 10000;
            } else if (areacode % 100 == 0) {
                //入参为市级调用区县统计
                areacode = areacode / 100;
            }
            districtCode = areacode + "";
        }
        return districtCode;
    }

    @Test
    public void str4() {
        Map<String, Object> map = this.checkMap("51149900");
        Map<String, Object> map2 = this.checkMap("51019900");
        Map<String, Object> map3 = this.checkMap("13990000");
        System.out.println(map.size());
        System.out.println(map2.size());
        System.out.println(map3.size());
    }

    /**
     * 处理请求报文，拼接产业链编码，省市县代码
     */
    private Map<String, Object> checkMap(String areaCode) {
        String provinceCode = areaCode.substring(0, 2) + "0000";
        String cityCode = areaCode.substring(0, 4) + "00";
        String districtCode = areaCode;
        if (areaCode.endsWith("0000")) {
            cityCode = "";
            districtCode = "";
        } else if (areaCode.endsWith("00")) {
            districtCode = "";
        }
        // 全国协商特定编码100000
        if ("10".equals(provinceCode.substring(0, 2))) {
            provinceCode = "";
            cityCode = "";
            districtCode = "";
        }
        if (areaCode.length() == 8) {
            if (areaCode.endsWith("0000")) {
                cityCode = areaCode.substring(0, 4) + "00";
            } else if (areaCode.endsWith("00")) {
                districtCode = areaCode;
            }
        }
        Map<String, Object> listMap = new HashMap<>();
        listMap.put("provinceCode", provinceCode);
        listMap.put("cityCode", cityCode);
        listMap.put("districtCode", districtCode);
        return listMap;
    }

    @Test
    public void str5() {
        List<String> list = new ArrayList<>();
//        list.add("110000");
//        list.add("110100");
//        list.add("110101");
//        list.add("120000");
//        list.add("120100");
//        list.add("120101");
//        list.add("130000");
//        list.add("130100");
        list.add("130102");
        list.add("510100");
        list.add("13990000");
        list.add("51019900");
        list.add("51149900");

        Set<String> largestRegion = this.findLargestRegion(list);
        System.out.println(largestRegion);
    }


    public Set<String> findLargestRegion(List<String> regionCodes) {
        Set<String> province = new HashSet<>();
        Set<String> city = new HashSet<>();
        Set<String> district = new HashSet<>();
        if (regionCodes.contains("100000")) {
            Set<String> set = new HashSet<>();
            set.add("100000");
            return set;
        }
        for (String code : regionCodes) {
            if (code.length() == 8) {
                if (code.endsWith("0000")) {
                    city.add(code);
                } else {
                    district.add(code);
                }
            } else {
                if (code.endsWith("0000")) {
                    province.add(code);
                } else if (code.endsWith("00")) {
                    city.add(code);
                } else {
                    district.add(code);
                }
            }
        }
        for (String item : district) {
            if (!city.contains(item.substring(0, 4) + "00")) {
                city.add(item);
            }
        }
        for (String item : city) {
            if (!province.contains(item.substring(0, 2) + "0000")) {
                province.add(item);
            }
        }
        return province;
    }

    /**
     * 判断区域类别，如果为0时为全国，为1时为省为2时为市为3时为区县
     */
    public static int judgeArea(String areaCode) {
        int code = 3;
        if (areaCode != null) {
            int areacode;
            if (areaCode.length() == 8) {
                areacode = Integer.valueOf(areaCode.substring(0,6));
            } else {
                areacode = Integer.valueOf(areaCode);
            }
            if (areacode == 100000) {
                //为全国时
                code = 0;
            } else if (areacode % 10000 == 0) {
                //入参为省级调用区县统计
                code = 1;
            } else if (areacode % 100 == 0) {
                //入参为市级调用区县统计
                code = 2;
            } else if (areaCode.startsWith("11") || areaCode.startsWith("31") || areaCode.startsWith("12") || areaCode.startsWith("50")) {
                //直辖市区县算市级
                code = 2;
            }
        }
        return code;
    }
}
