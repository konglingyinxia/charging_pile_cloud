package com.suda.platform.enums.lawcoin;

public enum LawCoinOrderStatusEnum {
    STATUS_1(1, "交易中"),
    STATUS_2(2, "已完成"),
    STATUS_3(3, "已撤销");
    private Integer code;
    private String message;

    LawCoinOrderStatusEnum(Integer code, String message) {

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

    public static LawCoinOrderStatusEnum getLawCoinOrderStatusEnumByCode(Integer code) {
        for (LawCoinOrderStatusEnum coin : LawCoinOrderStatusEnum.values()) {
            if (coin.getCode().equals(code)) {
                return coin;
            }
        }
        return null;
    }
}
