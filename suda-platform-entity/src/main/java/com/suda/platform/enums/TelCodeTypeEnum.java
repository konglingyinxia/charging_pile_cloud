package com.suda.platform.enums;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 手机验证码枚举类
 */
public enum TelCodeTypeEnum {
    REGISTER_TYPE((byte) 1, "注册"),
    RESET_PSWD((byte) 2, "忘记密码"),
    RENEW_PSWD((byte) 3, "安全验证"),
    TRADE_PSWD((byte) 4, "支付密码设置"),
    COIN_TYPE((byte) 5, "提币"),
    BIND_PHONE((byte) 6, "手机号绑定"),
    CHECK_PHONE((byte) 7, "手机号验证"),
    CHANGE_PHONE((byte) 8, "手机号更换")
    ;
    private Byte code;
    private String message;

    TelCodeTypeEnum(Byte code, String message) {

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

    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (TelCodeTypeEnum o : TelCodeTypeEnum.values()) {
            CODES.add(o.getCode());
        }
    }
}
