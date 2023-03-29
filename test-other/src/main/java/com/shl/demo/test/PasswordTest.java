package com.shl.demo.test;

public class PasswordTest {

    public static void main(String[] args) {
        String str = "sdfaed[][]3123A``~~s322323@";
        System.out.println(level(str));
    }

    /**
     * 长度
     */
    public static int length(String str) {
        if (str.length() < 5) {
            return 5;
        } else if (str.length() < 8) {
            return 10;
        } else {
            return 25;
        }
    }

    /**
     * 字母
     */
    public static int letters(String str) {
        int count1 = 0, count2 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
                count1++;
            }
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                count2++;
            }
        }
        if (count1 == 0 && count2 == 0) {
            return 0;
        }
        if (count1 != 0 && count2 != 0) {
            return 20;
        }
        return 10;
    }

    /**
     * 数字
     */
    public static int numbers(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        if (count == 1) {
            return 10;
        }
        return 20;
    }

    /**
     * 符号
     */
    public static int symbols(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 0x21 && str.charAt(i) <= 0x2F ||
                    str.charAt(i) >= 0x3A && str.charAt(i) <= 0x40 ||
                    str.charAt(i) >= 0x5B && str.charAt(i) <= 0x60 ||
                    str.charAt(i) >= 0x7B && str.charAt(i) <= 0x7E) {
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        if (count == 1) {
            return 10;
        }
        return 25;
    }

    /**
     * 奖励
     */
    public static int rewards(String str) {
        int letters = letters(str);
        int numbers = numbers(str);
        int symbols = symbols(str);
        // 字母和数字
        if (letters > 0 && numbers > 0 && symbols == 0) {
            return 2;
        }
        // 字母、数字和符号
        if (letters == 10 && numbers > 0 && symbols > 0) {
            return 3;
        }
        // 大小写字母、数字和符号
        if (letters == 20 && numbers > 0 && symbols > 0) {
            return 5;
        }
        return 0;
    }

    /**
     * 评分标准
     */
    public static String level(String str) {
        // 字母
        int letters = letters(str);
        // 长度
        int length = length(str);
        // 数字
        int numbers = numbers(str);
        // 符号
        int symbols = symbols(str);
        // 奖励
        int rewards = rewards(str);
        int sum = length + letters + numbers + symbols + rewards;
        if (sum >= 90) {
            // 非常安全
            return "VERY_SECURE";
        } else if (sum >= 80) {
            // 安全
            return "SECURE";
        } else if (sum >= 70) {
            // 非常强
            return "VERY_STRONG";
        } else if (sum >= 60) {
            // 强
            return "STRONG";
        } else if (sum >= 50) {
            // 一般
            return "AVERAGE";
        } else if (sum >= 25) {
            // 弱
            return "WEAK";
        } else {
            // 非常弱
            return "VERY_WEAK";
        }
    }
}
