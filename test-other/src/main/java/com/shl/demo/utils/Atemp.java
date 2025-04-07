package com.shl.demo.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.shl.demo.utils.domain.CoreEntInfo;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Atemp {
    public static void main(String[] args) {
        Date data = new Date();
        Date data1 = DateUtil.offsetDay(data,-3);
        DateTime beginOfDay = DateUtil.beginOfDay(data1);
        DateTime endOfDay = DateUtil.endOfDay(data);
        String s = "914401010881062980, " +
                "913310031482131095, " +
                "9133078208294192XA, " +
                "91320506579523395B, " +
                "91130944MA0E3BEU06, " +
                "91310120MAC9UKXQ85, " +
                "91141122330534881A, " +
                "91370211MA3DB0FW1E, " +
                "91141125MA0GRMBX9H, " +
                "91350203MA33MT8EXK, " +
                "91420120MA4KPXGX04, " +
                "913211913020239883, " +
                "91442000MA4WE6YB2F, " +
                "91331021336924047Q, " +
                "91500113MA5U3B2E73, " +
                "914403000886332661, " +
                "91320508799062965W, " +
                "91520100795258362N, " +
                "911101055790722135, " +
                "91440604MA51PAN90W";
        ArrayList<String> trades1 = new ArrayList<>(
                Arrays.asList(s.replaceAll("\\s+", "").split(",")));

//        List<CoreEntInfo> filteredList= new ArrayList<>();
//        CoreEntInfo coreEntInfo = new CoreEntInfo("1","1", "1");
//        CoreEntInfo coreEntInfo1 = new CoreEntInfo("1","2", "1");
//        CoreEntInfo coreEntInfo2 = new CoreEntInfo("1","1", "1,2,3");
//        CoreEntInfo coreEntInfo3 = new CoreEntInfo("1","1", "2");
//        filteredList.add(coreEntInfo);
//        filteredList.add(coreEntInfo1);
//        filteredList.add(coreEntInfo2);
////        filteredList.add(coreEntInfo3);
//
//        // 使用merge函数直接处理重复值
//        Map<String, CoreEntInfo> mergedMap = filteredList.stream()
//                .collect(Collectors.toMap(
//                        CoreEntInfo::getCoreEntOrgCode, // 分组key
//                        Function.identity(), // 值转换
//                        (existing, replacement) -> { // merge函数
//                            // 使用LinkedHashSet保持顺序并去重
//                            LinkedHashSet<String> trades = new LinkedHashSet<>(
//                                    Arrays.asList(existing.getTrade().split(","))
//                            );
//                            LinkedHashSet<String> trades1 = new LinkedHashSet<>(
//                                    Arrays.asList(replacement.getTrade().split(","))
//                            );
//                            trades.addAll(trades1);
//                            return new CoreEntInfo(
//                                    existing.getCoreEntName(),
//                                    existing.getCoreEntOrgCode(),
//                                    String.join(",", trades)
//                            );
//                        },
//                        LinkedHashMap::new // 保证顺序
//                ));
//        // 直接获取最终结果
//        List<CoreEntInfo> collect = new ArrayList<>(mergedMap.values());
//
//        System.out.println(collect.size());
    }

}
