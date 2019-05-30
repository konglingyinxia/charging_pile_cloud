package com.suda.platform.enums.product;

import com.shop.enums.comstatus.IsDeleteEnum;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 0:不是活动商品 1:是活动商品
 * @author kongling
 * @package com.shop.enums.product
 * @date 2019-05-08  16:57
 * @project suda_cloud
 */

public enum  IsActivityEnum {

    STATUS_1((byte)0 , "正常商品"),
    STATUS_2((byte) 1, "活动商品"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (IsDeleteEnum o : IsDeleteEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    IsActivityEnum(Byte code, String message) {

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
