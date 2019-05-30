package com.constant;

/**
 * @author: Zhang Zhengliang
 * @create: 2018-06-21 16:28
 **/
public class RedisKeysPrefix {


    /**
     * 存储活动商品 倒计时key
     */
    public  static  String PRODUCT_INFO_ACTIVE_COUNTDOWN="PRODUCT_INFO_ACTIVE_PRIZE_COUNTDOWN:"+ CommonConstant.project;

    /**
     * 存储短信信息
     */
    public  static  String SMS_SEND_TIME_MESSAGE="SMS_SEND_TIME_MESSAGE_:"+ CommonConstant.project;


    /********************************redis 通道 监听***************************************************/
    /**
     * redis 通道汇率传送
     */
    public static String VB_XEX_CHANGE_RATE_CHANNEL="vb:xExchangeRate:chan:"+CommonConstant.project;
    /**
     * redis 汇率数据
     *  添加单个币种 须追加code
     */
    public static String VB_XEX_CHANGE_RATE_DATA="vb:indexTickerAll:usd2cny:";


    //=============================redis 消息队列数据 法币交易交易记录===========================================================
    /**
     * 法币交易交易记录实时数据合成
     */
    public  static final  String FROM_LINKED_QUEUE_NEW_DATE_MESSAGE="FROM:LINKED:QUEUE:NEW:DATE:MESSAGE:LAW:COIN:DEAL:"+CommonConstant.project;


}
