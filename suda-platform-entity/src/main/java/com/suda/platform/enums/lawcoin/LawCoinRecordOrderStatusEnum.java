package com.suda.platform.enums.lawcoin;

public enum LawCoinRecordOrderStatusEnum {
    STATUS_1(1, "待付款"),
    STATUS_2(2, "已付款"),
    STATUS_3(3, "已完成"),
    STATUS_4(4, "申诉中"),
    STATUS_5(5, "已取消");
    private Integer code;
    private String message;

    LawCoinRecordOrderStatusEnum(Integer code, String message) {

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

    public static LawCoinRecordOrderStatusEnum getLawCoinRecordOrderStatusEnumByCode(Integer code) {
        for (LawCoinRecordOrderStatusEnum coin : LawCoinRecordOrderStatusEnum.values()) {
            if (coin.getCode().equals(code)) {
                return coin;
            }
        }
        return null;
    }
}
