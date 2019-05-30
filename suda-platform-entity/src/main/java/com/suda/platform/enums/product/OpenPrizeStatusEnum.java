package com.suda.platform.enums.product;

import com.shop.enums.comstatus.IsDeleteEnum;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 开奖状态
 * @author kongling
 * @package com.shop.enums.product
 * @date 2019-05-10  14:16
 * @project suda_cloud
 */
public enum  OpenPrizeStatusEnum {

    STATUS_1((byte)0 , "未开奖"),
    STATUS_2((byte) 1, "已开奖"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (IsDeleteEnum o : IsDeleteEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    OpenPrizeStatusEnum(Byte code, String message) {

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
