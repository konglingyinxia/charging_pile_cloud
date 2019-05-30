package com.suda.platform.enums.finance;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 * @author kongling
 * @package com.shop.enums.finance
 * @date 2019-05-09  10:14
 * @project suda_cloud
 */
public enum IncomeEnum {
    STATUS_0((byte) 0, "支出"),
    STATUS_1((byte) 1, "收入"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (IncomeEnum o : IncomeEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    IncomeEnum(Byte code, String message) {

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
