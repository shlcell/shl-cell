package com.shl.demo.test;

import java.util.HashMap;

/**
 * 规则表达式操作类
 */
public class RegularExpression {

    /**
     * 解析规则表达式
     *
     * @param expression 规则表达式
     * @param params     参数集合
     * @return 是否成立
     */
    public static boolean parse(String expression, HashMap<String, String> params) {
        // 不包含子表达式时，解析当前表达式
        if (!expression.contains("(")) {
            String[] keyValue = expression.split("=");
            String key = keyValue[0].trim();
            if (key.equals("") || keyValue.length < 2) {
                return false;
            }
            String value = keyValue[keyValue.length - 1].trim();

            if (key.equals("tax")) { // 税号分机号特殊逻辑
                String tax = params.get("tax");
                String taxMachine = tax + "." + params.get("machine");
                if (value.equals("{taxlist}")) { // 数据库白名单
                    String proid = params.get("proid");
                    String version = params.get("version");
                    // TODO  数据库查询 参考SQL：SELECT COUNT(1) FROM t
                    // TODO  WHERE proid = {proid} AND version = {version} AND tax IN ({tax}, {taxMachine})
                    return true;
                } else { // 规则直接配置
                    return value.equals(tax) || value.equals(taxMachine);
                }
            } else if (key.equals("area")) { // 地区编码特殊逻辑
                String area = params.get("area");
                return area.startsWith(value);
            } else { // 通用逻辑
                return value.equals(params.get(key));
            }
        }

        // 包含子表达式时，递归解析子表达式
        char opt = expression.charAt(0);
        int pre = 2; // 子表达式开始位置
        int count = 0;
        for (
                int i = 2; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                count++;
            } else if (i != expression.length() - 1 && expression.charAt(i) == ')') {
                count--;
            }
            if (count == 0 && (expression.charAt(i) == ',' || i == expression.length() - 1)) {
                // 左右括号匹配，为逗号，或者是最后，找到一个子表达式结束位置
                boolean sub = parse(expression.substring(pre, i), params);
                // 处理不同运算符
                if (opt == '!') {
                    return !sub;
                } else if (opt == '|' && sub) {
                    return true;
                } else if (opt == '&' && !sub) {
                    return false;
                }
                pre = i + 1;
            }
        }
        return opt == '&';
    }

    /**
     * 测试程序
     */
    public static void main(String[] args) {

        HashMap<String, String> params = new HashMap<>();
        params.put("proid", "test"); // 产品编号
        params.put("version", "1.0"); // 版本号
        params.put("tax", "440301000000001"); // 税号
        params.put("machine", "0"); // 分机号
        params.put("area", "440301"); // 地区编码
        params.put("nsrxz", "1"); // 纳税人性质

        String expression;
        boolean result;

        expression = "&(area=44,!(area=4403))";
        result = RegularExpression.parse(expression, params);
        System.out.println(expression + " 解析结果：" + result);

        expression = "|(area=12,tax={taxlist})";
        result = RegularExpression.parse(expression, params);
        System.out.println(expression + " 解析结果：" + result);

        expression = "&(nsrxz=2,tax={taxlist})";
        result = RegularExpression.parse(expression, params);
        System.out.println(expression + " 解析结果：" + result);
    }
}
