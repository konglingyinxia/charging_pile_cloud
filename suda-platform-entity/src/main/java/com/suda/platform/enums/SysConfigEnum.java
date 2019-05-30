package com.suda.platform.enums;

public enum SysConfigEnum {
    CONFIG_TYPE_1(1, "网站信息"),
    CONFIG_TYPE_2(2, "网站协议"),
    CONFIG_TYPE_3(3, "法币交易"),
    CONFIG_TYPE_6(6, "短信设置"),
    CONFIG_TYPE_7(7, "充/提设置"),
    CONFIG_TYPE_8(8, "平台账户"),
    CONFIG_TYPE_9(9, "关于我们邮箱"),;
    private Integer code;
    private String message;


    SysConfigEnum(Integer code, String message) {

        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}