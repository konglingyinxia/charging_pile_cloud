package com.suda.platform.enums.version;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author kongling
 * @package com.suda.platform.enums.version
 * @date 2019-05-29  11:33
 * @project suda_cloud
 */
public enum  ForcedUpdateEnum {

    STATUS_0((byte)0 , "不强更"),
    STATUS_1((byte) 1, "强更"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (ForcedUpdateEnum o : ForcedUpdateEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    ForcedUpdateEnum(Byte code, String message) {

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
