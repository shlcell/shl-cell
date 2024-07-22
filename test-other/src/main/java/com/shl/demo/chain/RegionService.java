package com.shl.demo.chain;

import java.util.*;

/**
 * 地区编码集合中最大地区权限的集合，100000>同省>同市>县
 */
public class RegionService {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        list.add("110000");
//        list.add("110100");
//        list.add("110101");
//        list.add("120000");
//        list.add("120100");
//        list.add("120101");
        list.add("130000");
//        list.add("130100");
        list.add("130102");
        list.add("510000");
        list.add("13990000");
        list.add("51019900");
        list.add("51149900");

        Set<String> largestRegion = findLargestRegion(list);
        System.out.println(largestRegion);
    }

    public static Set<String> findLargestRegion(List<String> regionCodes) {
        Set<String> province = new HashSet<>();
        Set<String> city = new HashSet<>();
        Set<String> district = new HashSet<>();
        if (regionCodes.contains("100000")) {
            Set<String> set = new HashSet<>();
            set.add("100000");
            return set;
        }
        for (String code : regionCodes) {
            if (code.endsWith("0000")) {
                province.add(code);
            } else if (code.endsWith("00")) {
                city.add(code);
            } else {
                district.add(code);
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

}
