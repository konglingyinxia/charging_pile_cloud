package com.suda.platform.enums.lawcoin;

import com.google.common.collect.Maps;

import java.util.Map;

public enum TradeTypeEnum {
    BUY((byte) 1, "买入(求购)"),
    SELL((byte) 2, "卖出(出售)");
    private Byte code;
    private String message;

    private static final Map<Byte, TradeTypeEnum> instances = Maps.newHashMap();

    static {
        for (TradeTypeEnum productTypeEnum : TradeTypeEnum.values()) {
            instances.put(productTypeEnum.getCode(), productTypeEnum);
        }
    }

    TradeTypeEnum(Byte code, String message) {

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

    public static TradeTypeEnum getBillPriceByCode(Byte code) {
        if (instances.containsKey(code)) {
            return instances.get(code);
        }

        return null;
    }
}