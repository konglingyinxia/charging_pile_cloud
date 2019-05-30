package com.suda.platform.enums.lawcoin;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author 卫星
 * @package com.sskj.common.constant
 * @date 2018-12-13  18:38
 * @project C2C
 */
public enum UpDownStatusEnum {
    STATUS_0(0, "上架"),
    STATUS_1(1, "下架"),;

    private Integer code;
    private String message;

    private static final Map<Integer, UpDownStatusEnum> instances = Maps.newHashMap();

    static {
        for (UpDownStatusEnum upDownStatusEnum : UpDownStatusEnum.values()) {
            instances.put(upDownStatusEnum.getCode(), upDownStatusEnum);
        }
    }

    UpDownStatusEnum(Integer code, String message) {

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

    public static UpDownStatusEnum getUpDownStatusEnum(Integer code) {
        if (instances.containsKey(code)) {
            return instances.get(code);
        }
        return null;
    }

}
