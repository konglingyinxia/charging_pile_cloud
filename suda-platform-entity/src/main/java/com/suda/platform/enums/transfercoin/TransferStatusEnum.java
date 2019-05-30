package com.suda.platform.enums.transfercoin;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 转账状态
 *
 * @author kongling
 * @package com.suda.platform.enums.transfercoin
 * @date 2019-05-24  16:59
 * @project suda_cloud
 */
public enum TransferStatusEnum {

    STATUS_0((byte)0 , "转账中"),
    STATUS_1((byte) 1, "转账成功"),
    STATUS_2((byte) 2, "转账失败"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (TransferStatusEnum o : TransferStatusEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    TransferStatusEnum(Byte code, String message) {

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
