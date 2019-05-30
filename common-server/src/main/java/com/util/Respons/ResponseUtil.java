package com.util.Respons;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 卫星 2017年9月28日 14:38:24
 */
public class ResponseUtil {

    /**
     * 总体请求
     *
     * @param normal
     * @param data
     * @param msg
     * @return
     */
    public static Map<String, Object> getResultMap(ResponseCode normal, Object data, String msg) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", Integer.valueOf(normal.toString()));
        resultMap.put("data", data);
        if (null == data) {
            resultMap.put("data", "");
        }
        resultMap.put("msg", msg);
        return resultMap;
    }

    /**
     * 返回操作成功
     */
    public static Map<String, Object> getSuccessMap() {
        return ResponseUtil.getResultMap(ResponseCode.NORMAL, "", ResponseMsg.OPERATION_SUCCESS);
    }

    /**
     * 返回操作成功
     */
    public static Map<String, Object> getSuccessMap(String msg) {
        return ResponseUtil.getResultMap(ResponseCode.NORMAL, "", msg);
    }

    /**
     * 查询数据正常返回
     */
    public static Map<String, Object> getSuccessMap(Object data, String msg) {
        return ResponseUtil.getResultMap(ResponseCode.NORMAL, data, msg);
    }

    /**
     * 查询数据正常返回
     */
    public static Map<String, Object> getSuccessMap(Object data) {
        return ResponseUtil.getResultMap(ResponseCode.NORMAL, data, ResponseMsg.OPERATION_SUCCESS);
    }

    /**
     * 查询数据无提示返回
     */
    public static Map<String, Object> getSuccessNoMsg(Object data) {
        return ResponseUtil.getResultMap(ResponseCode.NORMAL, data, "");
    }

    /**
     * 数据异常
     */
    public static Map<String, Object> getNotNormalMap(String msg) {
        return ResponseUtil.getResultMap(ResponseCode.NOT_NORMAL, "", msg);
    }

    /**
     * 业务异常
     *
     * @return
     */
    public static Map<String, Object> getNotNormalMap() {
        return ResponseUtil.getResultMap(ResponseCode.NOT_NORMAL, "", ResponseMsg.OPERATION_FAIL);
    }



    /**
     * 程序异常
     */
    public static Map<String, Object> getExceptionMap() {
        return ResponseUtil.getResultMap(ResponseCode.EXCEPTION, "", ResponseMsg.SERVER_EXCEPTION);
    }

    /**
     * 未登录
     *
     * @return
     */
    public static Map<String, Object> getNotLoginResponseMap() {
        return ResponseUtil.getResultMap(ResponseCode.NO_LOGIN, "", ResponseMsg.NOLOGIN);
    }

    /**
     * 令牌无效
     *
     * @return
     */
    public static Map<String, Object> getTokenErrResponseMap() {
        return ResponseUtil.getResultMap(ResponseCode.NO_LOGIN, "", ResponseMsg.TOKENERR);
    }
    /**
     * 令牌无效
     *
     * @return
     */
    public static Map<String, Object> getTokenOrParamsErrResponseMap() {
        return ResponseUtil.getResultMap(ResponseCode.NO_LOGIN, "", ResponseMsg.TOKEN_USER_ERR);
    }

    /**
     * 用户不可用
     *
     * @return
     */
    public static Map<String, Object> getDisableResponseMap() {
        return ResponseUtil.getResultMap(ResponseCode.NO_LOGIN, "", ResponseMsg.DISABLE);
    }

    /**
     * 账户或密码错误
     *
     * @return
     */
    public static Map<String, Object> loginFail() {
        return ResponseUtil.getResultMap(ResponseCode.NO_LOGIN, "", ResponseMsg.LOGINFAIL);
    }

    /**
     * 账户或密码错误
     *
     * @return
     */
    public static Map<String, Object> getNoUser() {
        return ResponseUtil.getResultMap(ResponseCode.NO_LOGIN, "", ResponseMsg.NOUSER);
    }

    /**
     * 用户踢出登录
     *
     * @return
     */
    public static Map<String, Object> getLoginOutResponseMap() {
        return ResponseUtil.getResultMap(ResponseCode.NO_LOGIN, "", ResponseMsg.LOGINOUT);
    }
    /**
     * 用户踢出登录
     *
     * @return
     */
    public static Map<String, Object> getNoLoginResponseMap() {
        return ResponseUtil.getResultMap(ResponseCode.NO_LOGIN, "", ResponseMsg.NOLOGIN_1);
    }
    /**
     * 用户重新登录
     *
     * @return
     */
    public static Map<String, Object> getLoginAgainResponseMap() {
        return ResponseUtil.getResultMap(ResponseCode.NO_LOGIN, "", ResponseMsg.LOGIN_AGAIN);
    }
   //登陆超时
    public static Map<String, Object> getLoginOutTimeMap() {
        return ResponseUtil.getResultMap(ResponseCode.NO_LOGIN, "", ResponseMsg.LOGINOUT_TIME_OUT);
    }
   //无权限
    public static Map<String, Object> NoAuth() {
        return ResponseUtil.getResultMap(ResponseCode.No_AUTH, "", ResponseMsg.NOAUTH);
    }
   //请求错误 commen异常
    public static Map<String, Object> RequestException(String msg) {
        return ResponseUtil.getResultMap(ResponseCode.NOT_NORMAL, "", msg);
    }
}
