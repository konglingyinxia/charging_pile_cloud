package com.suda.platform.enums.stockuser;

import com.google.common.collect.Maps;

import java.util.Map;

public enum OpenAccountStatusEnum {
    STATUS_1(1, "未认证"),
    STATUS_2(2, "待审核"),
    STATUS_3(3, "已认证"),
    STATUS_4(4, "审核未通过");
    private Integer code;
    private String message;

    private static final Map<Integer, OpenAccountStatusEnum> instances = Maps.newHashMap();

    static {
        for (OpenAccountStatusEnum productTypeEnum : OpenAccountStatusEnum.values()) {
            instances.put(productTypeEnum.getCode(), productTypeEnum);
        }
    }

    OpenAccountStatusEnum(Integer code, String message) {

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

    public static OpenAccountStatusEnum getOpenAccountStatusEnumByCode(Integer code) {
        if (instances.containsKey(code)) {
            return instances.get(code);
        }

        return null;
    }
}
