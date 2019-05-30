package com.suda.platform.entity;

import lombok.Data;
import lombok.ToString;

/**
 * 验证码类型
 */
@Data
@ToString
public class TelCodeTypeVO {
    private Byte codeType;// 1 注册 2 重置密码
    private String account;

}
