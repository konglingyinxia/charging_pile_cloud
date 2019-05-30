package com.suda.platform.enums.transfercoin;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 转账审核状态
 *
 * @author kongling
 * @package com.suda.platform.enums.transfercoin
 * @date 2019-05-24  17:03
 * @project suda_cloud
 */
public enum TransInspectStatusEnum {
    STATUS_0((byte)0 , "待审核"),
    STATUS_1((byte) 1, "已审核"),
    STATUS_2((byte) 2, "已拒绝"),
    STATUS_3((byte) 3, "审核中"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (TransInspectStatusEnum o : TransInspectStatusEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    TransInspectStatusEnum(Byte code, String message) {

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
