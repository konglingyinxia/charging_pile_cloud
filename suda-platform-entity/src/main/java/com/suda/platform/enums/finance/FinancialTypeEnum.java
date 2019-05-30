package com.suda.platform.enums.finance;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 财务类型
 */
public enum FinancialTypeEnum {
    TYPE_1(1, "平台充值"),
    TYPE_2(2, "CTC交易"),
    TYPE_3(3, "钱包转出"),
    TYPE_4(4, "手续费"),
    TYPE_5(5, "钱包转入"),
    TYPE_6(6, "钱包划转"),
    ;
    private Integer code;
    private String message;

    private static final Map<Integer, FinancialTypeEnum> instances = Maps.newHashMap();

    static {
        for (FinancialTypeEnum productTypeEnum : FinancialTypeEnum.values()) {
            instances.put(productTypeEnum.getCode(), productTypeEnum);
        }
    }

    FinancialTypeEnum(Integer code, String message) {

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

    public static FinancialTypeEnum getFinancialTypeEnumByCode(Integer code) {
        if (instances.containsKey(code)) {
            return instances.get(code);
        }

        return null;
    }
}
