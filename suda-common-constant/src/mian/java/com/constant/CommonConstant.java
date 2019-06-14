package com.constant;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

public class CommonConstant {

    /**
     * 平台订单随机位数
     */
    public static Integer RANDOM_LENGTH = 2;
    /**
     * 平台整体小数位保留数
     */
    public static Integer DECIMAL_PLACE = 4;

    /**
     * 其他币种的保留小数位
     */
    public static Integer DECIMAL_PLACE_EIGHT = 8;

    /**
     * 项目名
     */
    public static String project = "XX";

    /**
     * 邀请码起始值
     */
    public static String DAILI_INVITATIONCODE_START = "CG";

    /**
     * 账号前缀数字
     */
    public static String ACCOUNT_PREFIX = "88171";

    /**
     * 5分钟（单位毫秒）
     */
    public static final long VALIDITY_CODE_TIMEOUT = 5 * 60*1000;
    /**
     * 1分钟（单位毫秒）
     */
    public static final long VALIDITY_CODE_INTERVAL_TIMEOUT_1 =  60*1000;

    public static List<String> systemTypePC = Lists.newArrayList("PC");

    /** ****************特殊比例数据************/
    /**
     * 10
     */
    public static BigDecimal RATE_10 = new BigDecimal(10);
    /**
     * 100%
     */
    public static BigDecimal RATE_100 = new BigDecimal(100);
    /**
     * 1000%
     */
    public static BigDecimal RATE_1000 = new BigDecimal(1000);

    /**
     * 60秒
     */
    public static BigDecimal RATE_60 = new BigDecimal(60);

}