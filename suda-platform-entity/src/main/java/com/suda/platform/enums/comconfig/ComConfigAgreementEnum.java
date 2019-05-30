package com.suda.platform.enums.comconfig;

import com.google.common.collect.Lists;
import com.shop.enums.comstatus.IsDeleteEnum;

import java.util.List;

/**
 * 系统协议
 *
 * @author kongling
 * @package com.suda.platform.enums.comconfig
 * @date 2019-05-17  11:46
 * @project suda_cloud
 */

public enum  ComConfigAgreementEnum {
    STATUS_1((byte)1 , "关于我们"),
    STATUS_2((byte) 2, "注册协议"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (IsDeleteEnum o : IsDeleteEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    ComConfigAgreementEnum(Byte code, String message) {

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
