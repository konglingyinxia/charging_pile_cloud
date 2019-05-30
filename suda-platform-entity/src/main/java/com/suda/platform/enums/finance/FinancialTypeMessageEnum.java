package com.suda.platform.enums.finance;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 财务类型信息
 * @author kongling
 * @package com.shop.enums.finance
 * @date 2019-05-10  11:00
 * @project suda_cloud
 */
public enum  FinancialTypeMessageEnum {
    MESSAGE_1(1, "法币交易发布信息，余额扣除"),
    MESSAGE_2(2, "法币交易发布信息，手续费扣除"),
    MESSAGE_3(3, "法币交易求购单卖出，扣除卖出数量"),
    MESSAGE_4(4, "法币交易求购单卖出，扣除手续费"),
    MESSAGE_5(5, "法币交易确认卖家收款"),
    MESSAGE_6(6, "法币交易手动取消匹配交易!"),
    MESSAGE_7(7, "法币交易出售订单编辑，编辑数量比剩余数量多 扣除余额！"),
    MESSAGE_8(8, "法币交易出售订单编辑，编辑数量比剩余数量多 扣除手续费！"),
    MESSAGE_9(9, "法币交易出售订单编辑，编辑数量比剩余数量多 返还余额！"),
    MESSAGE_10(10, "法币交易出售订单编辑，编辑数量比剩余数量少 返还手续费！"),
    MESSAGE_11(11, "法币发布交易取消"),
    MESSAGE_12(12, "法币交易超时自动取消匹配交易!"),
    MESSAGE_13(13, "钱包转出，扣除余额"),
    MESSAGE_14(14, "钱包转出，扣除手续费"),
    MESSAGE_15(15, "钱包转入资产"),
    MESSAGE_16(16, "钱包划转，转出数量"),
    MESSAGE_17(17, "钱包划转，转出扣除手续费！"),
    MESSAGE_18(18, "钱包划转，转换币种数量！"),
    ;

    private Integer code;
    private String message;

    private static final Map<Integer, FinancialTypeMessageEnum> instances = Maps.newHashMap();

    static {
        for (FinancialTypeMessageEnum financialTypeMessageEnum : FinancialTypeMessageEnum.values()) {
            instances.put(financialTypeMessageEnum.getCode(), financialTypeMessageEnum);
        }
    }

    FinancialTypeMessageEnum(Integer code, String message) {

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

    public static FinancialTypeMessageEnum getFinancialTypeMessageEnum(Integer code) {
        if (instances.containsKey(code)) {
            return instances.get(code);
        }

        return null;
    }

}
