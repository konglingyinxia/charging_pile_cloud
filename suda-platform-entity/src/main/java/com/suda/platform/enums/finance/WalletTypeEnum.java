package com.suda.platform.enums.finance;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 钱包类型
 *
 * @author kongling
 * @package com.suda.platform.enums.finance
 * @date 2019-07-04  13:28
 * @project charging_pile_cloud
 */
public enum WalletTypeEnum {
    STATUS_1("ICCARD", "IC卡钱包"),
    STATUS_2("APPLET", "小程序钱包"),
    ;
    private String code;
    private String message;
    public static final List<String> CODES = Lists.newArrayList();
    static {
        for (WalletTypeEnum o : WalletTypeEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    WalletTypeEnum(String code, String message) {

        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
