package com.suda.platform.enums.lawcoin;

/**
 * 法币交易订单确认方式
 *
 * @author 卫星
 * @package com.sskj.common.constant
 * @date 2018-12-13  14:30
 * @project C2C
 */
public enum LawCoinConfirmTypeEnum {
    STATUS_1(1, "手动确认"),
    STATUS_2(2, "系统确认"),;
    private Integer code;
    private String message;

    LawCoinConfirmTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static LawCoinConfirmTypeEnum getLawCoinConfirmTypeEnum(Integer code) {
        for (LawCoinConfirmTypeEnum coin : LawCoinConfirmTypeEnum.values()) {
            if (coin.getCode().equals(code)) {
                return coin;
            }
        }
        return null;
    }
}
