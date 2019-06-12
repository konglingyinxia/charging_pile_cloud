package com.suda.platform.enums.finance;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 支付状态
 *
 * 1:未支付，2：支付成功 3：支付失败 4:处理中
 *
 * @author kongling
 * @package com.suda.platform.enums.transfercoin
 * @date 2019-05-24  17:03
 * @project suda_cloud
 */
public enum WithdrawStatusEnum {
    STATUS_1((byte)1 , "未支付"),
    STATUS_2((byte) 2, "支付成功"),
    STATUS_3((byte) 3, "支付失败"),
    STATUS_4((byte) 4, "处理中"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (WithdrawStatusEnum o : WithdrawStatusEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    WithdrawStatusEnum(Byte code, String message) {

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
