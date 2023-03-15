package com.shl.demo.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 枚举类
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SexEnum {

    MAN("00", "男"),
    WOMAN("01", "女"),
    MIDDLE("02", "中"),
    UNDEFINED("99", "未知"),
    ;

    private String key;

    private String value;

    SexEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 循环判断此code是否是某一类，返回其枚举 .getValue() 和 .getKey  获取key和value
     */
    public static SexEnum getValueByKey(String code) {
        for (SexEnum capitalEnum : SexEnum.values()) {
            if (capitalEnum.getKey().equals(code)) {
                return capitalEnum;
            }
        }
        return UNDEFINED;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
