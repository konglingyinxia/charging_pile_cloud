package com.suda.platform.enums.finance;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 财务类型信息
 * @author kongling
 * @package com.shop.enums.finance
 * @date 2019-05-10  11:00
 * @project suda_cloud
 */
public enum  FinancialTypeMessageEnum {
    MESSAGE_1(1, "充电消费金额"),
    MESSAGE_2(2, "充电消费服务费"),
    ;

    private Integer code;
    private String message;

    private static final Map<Integer, FinancialTypeMessageEnum> instances = Maps.newHashMap();

    static {
        for (FinancialTypeMessageEnum financialTypeMessageEnum : FinancialTypeMessageEnum.values()) {
            instances.put(financialTypeMessageEnum.getCode(), financialTypeMessageEnum);
        }
    }

    FinancialTypeMessageEnum(Integer code, String message) {

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

    public static FinancialTypeMessageEnum getFinancialTypeMessageEnum(Integer code) {
        if (instances.containsKey(code)) {
            return instances.get(code);
        }

        return null;
    }

}
