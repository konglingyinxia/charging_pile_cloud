package com.suda.platform.enums.transfercoin;

import com.google.common.collect.Maps;

import java.util.Map;

public enum ChargeTypeEnum {
    GOLD_IN((byte)1, "入金"),
    GOLD_OUT((byte)2, "出金");
    private Byte code;
    private String message;

    private static final Map<Byte, ChargeTypeEnum> instances = Maps.newHashMap();

    static {
        for (ChargeTypeEnum productTypeEnum : ChargeTypeEnum.values()) {
            instances.put(productTypeEnum.getCode(), productTypeEnum);
        }
    }

    ChargeTypeEnum(Byte code, String message) {

        this.code = code;
        this.message = message;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ChargeTypeEnum getChargeTypeEnumByCode(Integer code) {
        if (instances.containsKey(code)) {
            return instances.get(code);
        }

        return null;
    }
}
