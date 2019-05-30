package com.suda.platform.enums.finance;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 用户资产管理类型
 * @author kongling
 * @package com.shop.enums.stockuser
 * @date 2019-05-07  16:59
 * @project suda_cloud
 */
public enum StockUserAssetsManEnum {
    STATUS_1((byte) 1, "充值"),
    STATUS_2((byte) 2, "扣款"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (StockUserAssetsManEnum o : StockUserAssetsManEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    StockUserAssetsManEnum(Byte code, String message) {

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
