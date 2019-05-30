package com.suda.platform.enums.data;

public enum KLineTimeCycleEnum {
    MINUTE("minute"),
    MINUTE5("minute5"),
    MINUTE15("minute15"),
    MINUTE30("minute30"),
    MINUTE60("minute60"),
    DAY("day"),
    WEEK("week"),
    MONTH("month");
    public String value;

    KLineTimeCycleEnum(String str) {
        this.value = str;
    }

}
