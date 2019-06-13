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



    /*********************** 短信发送 ********************************/
    public static  String SMS_NO_TIME_MINUTE_1="请等60s后在发送！";

    /************************支付方式**********************************/
    public static  String PAY_WAY_TYPE_NO="没有该支付类型！";

    //===================修改密码===============================
    public static String OLD_NEW_PASSWORD_NOT_EQUAL="新密码与原密码不能相同";
    public static String OLD_PASSWORD_IS_ERROR="原密码错误";




}
