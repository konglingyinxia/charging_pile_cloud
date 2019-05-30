package com.util.auth;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author 张子艺
 * @packge com.util.auth
 * @data 2019-02-19 11:55
 * @project CurrenCy-Cloud
 */
@Slf4j
public class AuthSign {
    /**
     * 认证token 键
     */
    public static   String token = "token";

    /**
     * 加密值
     */
    private static    String SECRET="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIjat4xxBhSDFzDiDORq5GnK2AwS";

    /**
     * 设置JWT初始信息
     */
    private static    Map<String,Object> jwtHeader = Maps.newHashMapWithExpectedSize(2);
    static {
        jwtHeader.put("typ","JWT");
        jwtHeader.put("alg","HS256");
    }

    /**
     * 加密用户id
     */
    private static  String ID ="id";
    /**
     * 加密用户信息
     */
    private static  String OBJECT = "object";

    /**
     * 变化时间
     *
     */
    private  static  String DATE = "date";
    /**
     * 过期时间使用redis 的过期时间
     * @param id 用户id
     * @param object 用户信息
     * @return
     */
    public static  String  tokenSign(Long id, JSONObject object) throws UnsupportedEncodingException {
        //私钥及加密算法
        Algorithm algorithm=Algorithm.HMAC256(SECRET);
        return  JWT.create().withHeader(jwtHeader)
                .withClaim(ID,id)
                .withClaim(OBJECT,object.toJSONString())
                .withClaim(DATE,System.currentTimeMillis())
                .sign(algorithm);
    }

    /**校验token
     *
     * @param token
     * @return
     */
    public  static  boolean verify(String token) {
        try {
            Algorithm algorithm= Algorithm.HMAC256(SECRET);
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.info("token校验失败："+ExceptionUtils.getStackTrace(e));
            return false;
        }

    }
    /**
     * 获取用户id信息
     */
    public  static Long  getUserId(String token) {
        try {
            Algorithm algorithm= Algorithm.HMAC256(SECRET);
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
           return jwt.getClaim(ID).asLong();
        } catch (Exception e) {
           log.info("获取用户id错误：%s", ExceptionUtils.getStackTrace(e));
        }
        return 0L;
    }
    /**
     * 获取用户信息
     */
    public  static JSONObject  getUserObject(String token) {
        try {
            Algorithm algorithm= Algorithm.HMAC256(SECRET);
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
            return JSONObject.parseObject(jwt.getClaim(OBJECT).asString());
        } catch (Exception e) {
            log.info("获取用户id错误：%s", ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * app获取接口用户信息
     * @return
     *//*
    public  static InterfaceUserInfoVO  getInterfaceUserInfoVO(String token){
        try {
            Algorithm algorithm= Algorithm.HMAC256(SECRET);
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
            JSONObject  jsonObject =   JSONObject.parseObject(jwt.getClaim(OBJECT).asString());
            InterfaceUserInfoVO vo= JSONObject.toJavaObject(jsonObject,InterfaceUserInfoVO.class);
            return vo;
        } catch (Exception e) {
            log.info("获取用户id错误：%s", ExceptionUtils.getStackTrace(e));
        }
        return null;
    }*/

   /* public static void main(String[] args) throws UnsupportedEncodingException {
        StockUserVO stockUserVO = new StockUserVO();
        stockUserVO.setId(1L);
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(stockUserVO));
        InterfaceUserInfoVO vo = JSONObject.toJavaObject(jsonObject,InterfaceUserInfoVO.class);
        System.out.println(vo);

        *//*String sign = "";
        System.out.println(sign);
        Algorithm algorithm= Algorithm.HMAC256(SECRET);
        JWTVerifier verifier=JWT.require(algorithm).build();
        DecodedJWT jwt=verifier.verify(sign);
        System.out.println("hedder:"+jwt.getHeader());
        System.out.println("getClaims:"+jwt.getClaims());
        System.out.println("getExpiresAt:"+jwt.getExpiresAt());*//*

        System.out.println("sign:"+tokenSign(10L, new JSONObject()));
    }*/
}
