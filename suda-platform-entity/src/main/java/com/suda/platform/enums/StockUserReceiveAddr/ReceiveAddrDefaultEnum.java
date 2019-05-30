package com.suda.platform.enums.StockUserReceiveAddr;

import com.shop.enums.comstatus.IsDeleteEnum;
import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 * 用户默认地址状态
 * @author kongling
 * @package com.shop.enums.StockUserReceiveAddr
 * @date 2019-05-11  10:40
 * @project suda_cloud
 */
public enum ReceiveAddrDefaultEnum {
    STATUS_1((byte)0 , "普通"),
    STATUS_2((byte)1, "默认"),
    ;
    private Byte code;
    private String message;
    public static final List<Byte> CODES = Lists.newArrayList();
    static {
        for (IsDeleteEnum o : IsDeleteEnum.values()) {
            CODES.add(o.getCode());
        }
    }
    ReceiveAddrDefaultEnum(Byte code, String message) {

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
