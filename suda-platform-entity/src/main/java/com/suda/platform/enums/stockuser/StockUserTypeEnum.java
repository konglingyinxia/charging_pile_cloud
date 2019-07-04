package com.suda.platform.enums.stockuser;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 用户类型
 *
 * @author kongling
 * @package com.suda.platform.enums.stockuser
 * @date 2019-07-04  14:21
 * @project charging_pile_cloud
 */
public enum StockUserTypeEnum {

    STATUS_1(1, "小程序用户"),
    STATUS_2(2, "IC 卡用户"),;
    private Integer code;
    private String message;

    private static final Map<Integer, StockUserTypeEnum> instances = Maps.newHashMap();

    static {
        for (StockUserTypeEnum productTypeEnum : StockUserTypeEnum.values()) {
            instances.put(productTypeEnum.getCode(), productTypeEnum);
        }
    }

    StockUserTypeEnum(Integer code, String message) {

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

    public static StockUserTypeEnum getOpenAccountStatusEnumByCode(Integer code) {
        if (instances.containsKey(code)) {
            return instances.get(code);
        }
        return null;
    }
}
