package com.suda.platform.enums.lawcoin;

public enum LawCoinAppealStatusEnum {
    STATUS_1(1, "进行中"),
    STATUS_2(2, "已判决"),
    STATUS_3(3, "已撤销");
    private Integer code;
    private String message;

    LawCoinAppealStatusEnum(Integer code, String message) {

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

    public static LawCoinAppealStatusEnum getLawCoinAppealStatusEnumByCode(Integer code) {
        for (LawCoinAppealStatusEnum coin : LawCoinAppealStatusEnum.values()) {
            if (coin.getCode().equals(code)) {
                return coin;
            }
        }
        return null;
    }
}
