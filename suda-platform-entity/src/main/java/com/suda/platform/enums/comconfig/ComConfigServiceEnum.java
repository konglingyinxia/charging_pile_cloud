package com.suda.platform.enums.comconfig;

/**
 * 菜单级别
 * @author kongling
 * @package com.shop.enums.RolePermission
 * @date 2019-05-06  15:30
 * @project suda_cloud
 */
public enum ComConfigServiceEnum {
    STATUS_1((byte) 1, "QQ"),
    STATUS_2((byte) 2, "微信"),
    STATUS_3((byte) 3, "邮箱"),
    STATUS_4((byte) 4, "公众号"),
    STATUS_5((byte) 5, "新浪微博"),
    STATUS_6((byte) 6, "电报群"),;
    private Byte code;
    private String message;

    ComConfigServiceEnum(Byte code, String message) {

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
