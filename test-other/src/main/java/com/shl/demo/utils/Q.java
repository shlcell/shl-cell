package com.shl.demo.utils;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class Q {
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
        //拼装全部节点名称
        ArrayList<NodeCode> nodeCodes = new ArrayList<>();
        NodeCode nodeCode1 = new NodeCode();
        nodeCode1.setNodeCode("N_QN_1_1_J_4_1_6");
        nodeCode1.setIndustryCode("N_QN_1_1");
        nodeCode1.setNodeName("测试");
        NodeCode nodeCode2 = new NodeCode();
        nodeCode2.setNodeCode("N_QN_1_1_J_4_1_6");
        nodeCode2.setIndustryCode("N_QN_1_1");
        nodeCode2.setNodeName("测试1");
        NodeCode nodeCode3 = new NodeCode();
        nodeCode3.setNodeCode("N_QN_1_1_J_4_1_6");
        nodeCode3.setIndustryCode("N_QN_1_1");
        nodeCode3.setNodeName("测试");
        nodeCodes.add(nodeCode1);
        nodeCodes.add(nodeCode2);
        nodeCodes.add(nodeCode3);
        if (nodeCodes != null) {
            JSONArray jsonArray = JSONArray.from(nodeCodes);
            HashSet<String> nodeNameSet = new HashSet<>();
            for (Object object : jsonArray) {
                JSONObject json = JSONObject.from(object);
                Object result = json.get("nodeName");
                if (result != null) {
                    nodeNameSet.add(result.toString());
                }
            }
            String nodesName = null;
            if (nodeNameSet.size() > 0) {
                nodesName = String.join(", ", nodeNameSet);
            }
//            StringBuffer stringBuffer = new StringBuffer();
//            HashSet<String> nodeNameSet = new HashSet<>(); // 用于存储节点名称并去重
//            for (Object object : jsonArray) {
//                JSONObject json = JSONObject.from(object);
//                String result = json.getString("nodeName");
//                if (result != null) {
//                    stringBuffer.append(result.toString() + ",");
//                    nodeNameSet.add(result); // 将节点名称添加到集合中
//                }
//            }
//            String nodesName = null;
//            String nodesName1 = null;
//            if (!stringBuffer.toString().isEmpty()) {
//                nodesName = stringBuffer.toString();
//                nodesName = nodesName.substring(0, nodesName.length() - 1);
//                nodesName1 = String.join(", ", nodeNameSet); // 使用逗号连接节点名称
//            }
//            System.out.println(nodesName);
            System.out.println(nodesName);

        }
    }
}
