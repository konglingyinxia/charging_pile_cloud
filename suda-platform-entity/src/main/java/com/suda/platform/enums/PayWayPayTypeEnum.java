package com.suda.platform.enums;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 用户添加支付方式
 */
public enum PayWayPayTypeEnum {

    STATUS_1(1, "微信"),
    STATUS_2(2, "支付宝"),
    STATUS_3(3, "银行卡");

    private Integer code;
    private String message;
    public static final List<Integer> CODES = Lists.newArrayList();

    private static final Map<Integer, PayWayPayTypeEnum> instances = Maps.newHashMap();

    static {
        for (PayWayPayTypeEnum productTypeEnum : PayWayPayTypeEnum.values()) {
            instances.put(productTypeEnum.getCode(), productTypeEnum);
        }
        for (PayWayPayTypeEnum o : PayWayPayTypeEnum.values()) {
            CODES.add(o.getCode());
        }
    }

    PayWayPayTypeEnum(Integer code, String message) {

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

    public static PayWayPayTypeEnum getPayTypeEnumByCode(Integer code) {
        if (instances.containsKey(code)) {
            return instances.get(code);
        }

        return null;
    }
}
