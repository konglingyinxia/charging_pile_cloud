package com.util.Respons;

/**
 * Controller响应信息
 *
 * @author rzcllove
 */

public class ResponseMsg {

    /**
     * NULL 没有响应信息
     */
    public static String NULL = "";

    /**
     * 上传文件失败
     */
    public static  String UPLOAD_FAIL="上传失败,请稍后重试!";

    /**
     * 用户不存在
     */
    public static String LOGINFAIL = "账户或密码错误";
    public static String NOUSER = "账户不存在";

    public static String ID_IS_EMPTY = "id为空";

    /**
     * 该帐号已经存在
     */
    public static String USER_HAS_EXIST = "该帐号已经存在";

    /**
     * 未登录
     */
    public static String NOLOGIN = "你当前未登录,禁止请求";
    /**
     * 令牌无效
     */
    public static String TOKENERR = "令牌无效";
    /**
     * 令牌与用户不匹配
     */
    public static String TOKEN_USER_ERR = "令牌与用户不匹配";
    /**
     * 账户不可用
     */
    public static String DISABLE = "当前账户不可用";
    /**
     * 无权限操作
     */
    public static String NOAUTH = "无权限操作";
    /**
     * 踢出用户
     */
    public static String LOGINOUT = "当前账号已在其他设备登录";
    /**
     * 用户未登录
     */
    public static String NOLOGIN_1 = "用户未登录";
    /**
     * 用户重新登录
     */
    public static String LOGIN_AGAIN = "请重新登录";
    /**
     * 登录超时
     */
    public static String LOGINOUT_TIME_OUT = "登录超时,请重新登录!";

    /**
     * 操作成功
     */
    public static String OPERATION_SUCCESS = "操作成功";
    /**
     * 操作失败
     */
    public static String OPERATION_FAIL = "操作失败";
    /**
     * 服务器异常
     */
    public static String SERVER_EXCEPTION = "服务器繁忙，请稍候重试";
    /**
     * 无效参数
     */
    public static String ILLEGAL_PARAM = "无效参数";
    public static String MISS_PARAM = "缺少参数";
    public static String ERROR_PARAM = "参数错误，请检查参数";
    public static String DATE_ERROR_FORMAT = "日期格式错误";
    /**
     * 无效id
     */
    public static String ILLEGAL_PARAM_ID = "ID不存在";
    /**
     * 参数为空
     */
    public static String EMPTY_PARAM = "参数为空";


    /**
     * 用户注册登录相关提示
     */
    public static String INVITATIONCODE_ERROR = "邀请码错误!";
    public static String INVITATIONCODE_ISEMPTY = "邀请码未填写";
    public static String REGISTER_FILE = "用户注册失败！";
    public static String REGISTER_EMAIL_FILE = "用户邮箱绑定失败！";
    public static String SEND_EMAIL_FILE = "邮箱发送邮件失败！";
    public static String HIGH_USER_STATUS = "邀请码无效！";
    public static String CANCLE_EMAIL_FILE = "用户邮箱解绑失败！";

    /**
     * 交易相关
     */
    public static String DEAL_PWD_ERROR = "交易密码错误！";
    public static String DEAL_BILL_ERROR = "订单信息错误！";
    public static String DEAL_NUM_LITTER = "交易数量不足！";
    public static String DEAL_COIN_LITTER = "账户%s资产不足！";
    public static String BILL_NOT_CANCEL = "订单已经不能取消";
    public static String BILL_HAVE_CANCEL = "订单已经取消";

    /************************角色*************************************/

    public static  String ROLE_HAVE_EXIST="该角色已经存在";
    public static  String ROLE_HAVE_OCCUPY_NO_DEL="该角色被占用，不能删除";

    /***********************权限**************************************/
    public static  String PERMISSION_NO_HAVE_PARENT_ID="该权限的父级id不存在！";
    /***********************后台会员管理**************************************/
    public static  String ADMIN_MANAGE_NO_STOCK="平台没有该币种，请检查币种编码是否正确！";
    public static  String ADMIN_MANAGE_RECHARGE_OPERATION="没有该类型操作！";

    /***********************普通商品管理***************************/
    public static  String ADMIN_MANAGE_NO_PRODUCT_ID="商品id没有传入！";
    public static  String ADMIN_MANAGE_NO_PRODUCT="没有该商品！";
    public static  String ADMIN_MANAGE_PRODUCT_IS_ACTIVE="该商品是活动商品！";

    /***********************抽奖商品管理***************************/
    public static  String DRAW_PRIZE_NO_EXIST="该商品不是活动商品！";
    public static  String DRAW_PRIZE_APPLY_FAIL="抽奖商品报名失败,稍后重试！";
    public static  String DRAW_PRIZE_DATE_OUT="该商品已经开奖，不能报名！";
    public static  String DRAW_PRIZE_USER_HAVE_TAKE="该用户已经参与该商品抽奖！";
    public static  String DRAW_PRIZE_GET_PRODUCT_FAIL="领取抽奖商品失败！";
    public static  String DRAW_PRIZE_GET_PRODUCT_HAVE="该奖品已经领取！";
    public static  String DRAW_PRIZE_ACTIVE_NO_EXIST="参与记录异常！";
    /***********************大转盘*******************************/
    public static  String TURNPLATE_DRAW_PRIZE_FAIL="抽奖失败，请稍后重试！";

    /*********************** 短信发送 ********************************/
    public static  String SMS_NO_TIME_MINUTE_1="请等60s后在发送！";

    /************************支付方式**********************************/
    public static  String PAY_WAY_TYPE_NO="没有该支付类型！";

    //============================认证交易相关=============
    public static String NO_DEAL_NO_AUTH_FINISH = "实名认证未完成，暂不能交易";

    public static String NO_DEAL_NOW_CODE = "该币种已不能交易";

    public static String GET_USER_INFO_FAIL = "获取用户信息失败，稍后重试";

    public static String BILL_NO_CANCEL_NO_DELETE = "订单未撤销，不能删除！";

    public static String NO_EDIT_RATE_NO_EQUAL = "服务费比例发生变化，请重新发单";
    public static String DEAL_NUM_NO_LESS_THAN_ZERO = "交易数量不能小于等于0";

    //============================地址管理===================
    public static String ADDR_HAS_EXIST = "该地址已经存在！";
    public static String NO_HAS_CODE = "没有该币种！";
    public static String ADDR_IS_NO_VALID = "该地址为无效地址";
    public static String DELETE_FAIL = "地址删除失败，请检查是否是有效地址";
    public static String CREATE_ADDR_FAIL = "创建地址失败，稍后重试";

    //====================转账 划转==================================
    public static String TRANSFER_NUMBER_NOT_ENOUGH  = "转出%s数量不足！";
    public static String TRANSFER_NOT_TO_SELF = "自己不能转给自己！";
    public static String TRANSFER_RATIO_NOT_EXIST = "划转汇率不正确，请联系管理员确认！";
    public static String TRANSFER_NOT_EQ_CODE = "相等币种之间不能划转！";

}
