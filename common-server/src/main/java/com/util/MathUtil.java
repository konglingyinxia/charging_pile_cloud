package com.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yanjiao
 */
@Slf4j
public class MathUtil {

    /**
     * 获取随机的数值。
     *
     * @param length 长度
     * @return
     */
    public static String getRandom620(Integer length) {
        String result = "";
        Random rand = new Random();
        int n = 20;
        if (null != length && length > 0) {
            n = length;
        }
        boolean[] bool = new boolean[n];
        int randInt = 0;
        for (int i = 0; i < length; i++) {
            do {
                randInt = rand.nextInt(n);

            } while (bool[randInt]);

            bool[randInt] = true;
            result += randInt;
        }
        return result;
    }

    /**
     * MD5 加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            log.info("MD5转换异常！message:" + e.getMessage());
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }

    /**
     * 生成范围随机数
     */
    public static BigDecimal getOriginBoundRandom(BigDecimal min, BigDecimal max) {
        Double d = ThreadLocalRandom.current().nextDouble(min.doubleValue(), max.doubleValue());
        BigDecimal bigDecimal = new BigDecimal(d).setScale(8, RoundingMode.DOWN);
        return bigDecimal;
    }

    /**
     * 创建指定数量的随机字符串
     *
     * @param numberFlag 是否是数字
     * @param length
     * @return
     */
    public static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return retStr;
    }

}