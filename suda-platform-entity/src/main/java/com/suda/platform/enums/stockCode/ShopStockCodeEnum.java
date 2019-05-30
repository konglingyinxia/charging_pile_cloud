package com.suda.platform.enums.stockCode;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author kongling
 * @package com.shop.enums.stockCode
 * @date 2019-05-13  10:21
 * @project suda_cloud
 */
public enum ShopStockCodeEnum {
    STATUS_1((byte)1 , "TC"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (ShopStockCodeEnum o : ShopStockCodeEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    ShopStockCodeEnum(Byte code, String message) {

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


}
