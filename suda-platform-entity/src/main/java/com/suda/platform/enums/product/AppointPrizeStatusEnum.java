package com.suda.platform.enums.product;

import com.shop.enums.comstatus.IsDeleteEnum;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 指定中奖状态
 * @author kongling
 * @package com.shop.enums.product
 * @date 2019-05-13  16:40
 * @project suda_cloud
 */

public enum AppointPrizeStatusEnum {

    STATUS_0((byte)0 , "未指定"),
    STATUS_1((byte) 1, "指定"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (IsDeleteEnum o : IsDeleteEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    AppointPrizeStatusEnum(Byte code, String message) {

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
