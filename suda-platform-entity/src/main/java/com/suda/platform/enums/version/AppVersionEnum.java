package com.suda.platform.enums.version;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @author kongling
 * @package com.suda.platform.enums.version
 * @date 2019-05-29  11:04
 * @project suda_cloud
 */
public enum AppVersionEnum {

    STATUS_1((byte)1, "ios"),
    STATUS_2((byte)2, "android"),
  ;

    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();

    private static final Map<Byte, AppVersionEnum> instances = Maps.newHashMap();

    static {
        for (AppVersionEnum appVersionEnum : AppVersionEnum.values()) {
            instances.put(appVersionEnum.getCode(), appVersionEnum);
        }
        for (AppVersionEnum o : AppVersionEnum.values()) {
            CODES.add(o.getCode());
        }
    }

    AppVersionEnum(Byte code, String message) {

        this.code = code;
        this.message = message;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static AppVersionEnum getPayTypeEnumByCode(byte code) {
        if (instances.containsKey(code)) {
            return instances.get(code);
        }

        return null;
    }
}
