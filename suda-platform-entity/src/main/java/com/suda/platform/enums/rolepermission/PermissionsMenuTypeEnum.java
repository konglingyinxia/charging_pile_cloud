package com.suda.platform.enums.rolepermission;

/**
 * 菜单权限类型
 * @author kongling
 * @package com.shop.enums
 * @date 2019-05-06  15:26
 * @project suda_cloud
 */
public enum PermissionsMenuTypeEnum {

    MENU_TYPE_1((byte) 1, "菜单"),
    MENU_TYPE_2((byte) 2, "按钮"),;
    private Byte code;
    private String message;

    PermissionsMenuTypeEnum(Byte code, String message) {

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
