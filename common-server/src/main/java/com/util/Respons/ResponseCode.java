package com.util.Respons;

public enum ResponseCode {

    NORMAL(0),//正常响应
    NOT_NORMAL(1),//业务异常
    EXCEPTION(-1),//程序异常
    No_AUTH(101),//无权限
    NO_LOGIN(100);//重新登录


    private Integer code;

    private ResponseCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }

}
