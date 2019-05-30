package com.shop.enums.comstatus;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 是否已经删除
 * @author kongling
 * @package com.shop.enums.stockuser
 * @date 2019-05-07  16:59
 * @project suda_cloud
 */
public enum IsDeleteEnum {
    STATUS_1((byte)0 , "未删除"),
    STATUS_2((byte) 1, "删除"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (IsDeleteEnum o : IsDeleteEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    IsDeleteEnum(Byte code, String message) {

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
