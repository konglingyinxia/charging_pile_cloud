package com.suda.platform.enums.transfercoin;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 是否是平台地址
 *
 * @author kongling
 * @package com.suda.platform.enums.transfercoin
 * @date 2019-05-24  17:02
 * @project suda_cloud
 */
public enum  PlatformIsAddrEnum {
    STATUS_0((byte)0 , "不是平台地址"),
    STATUS_1((byte) 1, "是平台地址"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (PlatformIsAddrEnum o : PlatformIsAddrEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    PlatformIsAddrEnum(Byte code, String message) {

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
