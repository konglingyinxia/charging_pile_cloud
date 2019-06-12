package com.suda.platform.enums.finance;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 付款方式
 *   支付类型：1.微信 2.线下
 * @author kongling
 * @package com.suda.platform.enums.finance
 * @date 2019-06-12  10:36
 * @project charging_pile_cloud
 */
public enum PayTypeEnum {

    STATUS_1((byte) 1, "微信"),
    STATUS_2((byte) 2, "线下"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (PayTypeEnum o : PayTypeEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    PayTypeEnum(Byte code, String message) {

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
