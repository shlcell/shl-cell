package com.shl.demo.utils;

import java.util.*;
import java.util.stream.Collectors;

public class X {
    public static List<String> getStrings(String str) {
        if (null == str) return null;
        if ("".equals(str)) {
            str = "[\"\"]";
            return new ArrayList<String>();
        }
        String cleanedStr = str.substring(1, str.length() - 1);
        cleanedStr = cleanedStr.replace("\"", "");
        String[] elements = cleanedStr.split(",");
//        List<String> list = Arrays.asList(elements);
        List<String> list = new ArrayList<>(Arrays.asList(elements));
        if (list.size() < 12) {
            int num = 12 - list.size();
            for (int i = 0; i < num; i++) {
                list.add("0");
            }
        }
        return list;
    }

    public static void main(String[] args) {
//        String s ="[\"0\",\"1\",\"2\"]";
//        String s =null;
//        List<String> strings = getStrings(s);
//        System.out.println(strings);
//        SalesReportSalesYearNumbersAmounts invoiceStatInfo = new SalesReportSalesYearNumbersAmounts();
//        invoiceStatInfo.setNumbers(strings);
//        System.out.println(invoiceStatInfo);
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("recommend", 1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("recommend", 4);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("recommend", 3);
        list.add(map);
        list.add(map2);
        list.add(map3);
        List<Map<String, Object>> mapList = list.stream()
                .sorted(Comparator.comparing((Map<String, Object> m) -> (Integer) m.get("recommend")).reversed())
                .collect(Collectors.toList());
        System.out.println(mapList);
    }
}
