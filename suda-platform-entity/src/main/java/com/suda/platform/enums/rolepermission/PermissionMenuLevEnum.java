package com.suda.platform.enums.rolepermission;

/**
 * 菜单级别
 * @author kongling
 * @package com.shop.enums.rolepermission
 * @date 2019-05-06  15:30
 * @project suda_cloud
 */
public enum PermissionMenuLevEnum {
    MENU_LEV_1((byte) 1, "一级菜单"),
    MENU_LEV_2((byte) 2, "二级菜单"),;
    private Byte code;
    private String message;

    PermissionMenuLevEnum(Byte code, String message) {

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
