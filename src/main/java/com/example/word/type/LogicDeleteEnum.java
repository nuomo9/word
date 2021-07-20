package com.example.word.type;

/**
 * 逻辑删除枚举
 *
 * @author liuyuhui
 * @since 2020-10-25
 */
public enum LogicDeleteEnum {
    /**
     * 未删除
     */
    UN_DELETE(0, "未删除", false),
    /**
     * 已删除
     */
    DELETED(1, "已删除", true);


    private Integer code;
    private String value;
    private Boolean status;

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public Boolean getStatus() {
        return status;
    }

    LogicDeleteEnum(Integer code, String value, Boolean status) {
        this.code = code;
        this.value = value;
        this.status = status;
    }

    public static LogicDeleteEnum getType(Integer code) {
        for (LogicDeleteEnum type : LogicDeleteEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    public static LogicDeleteEnum getType(String value) {
        for (LogicDeleteEnum type : LogicDeleteEnum.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getValueByCode(Integer code) {
        LogicDeleteEnum type = getType(code);
        if (type != null) {
            return type.value;
        }
        return null;
    }

    public static Integer getCodeByValue(String value) {
        LogicDeleteEnum type = getType(value);
        if (type != null) {
            return type.code;
        }
        return null;
    }
}
