package com.suda.platform.enums.finance;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author kongling
 * @package com.shop.enums.finance
 * @date 2019-05-08  10:09
 * @project suda_cloud
 */
public enum WaterTypeEnum {

    STATUS_1((byte) 1, "后台操作"),
    STATUS_2((byte) 2, "正常流水"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (StockUserAssetsManEnum o : StockUserAssetsManEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    WaterTypeEnum(Byte code, String message) {

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
