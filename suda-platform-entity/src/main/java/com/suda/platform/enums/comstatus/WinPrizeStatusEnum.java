package com.suda.platform.enums.comstatus;

import com.google.common.collect.Lists;
import com.shop.enums.comstatus.IsDeleteEnum;

import java.util.List;

/**
 * 中奖状态 0:未中奖 1:中奖
 * @author kongling
 * @package com.shop.enums.comstatus
 * @date 2019-05-13  11:05
 * @project suda_cloud
 */
public enum WinPrizeStatusEnum {
    STATUS_0((byte)0 , "未中奖"),
    STATUS_1((byte) 1, "已中奖"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (IsDeleteEnum o : IsDeleteEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    WinPrizeStatusEnum(Byte code, String message) {

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
