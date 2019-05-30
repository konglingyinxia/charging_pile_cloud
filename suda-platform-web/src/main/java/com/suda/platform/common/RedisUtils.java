package com.suda.platform.common;

import com.constant.CommonConstant;
import com.constant.RedisDbIndexConst;
import com.constant.RedisKeysPrefix;
import config.com.MyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author kongling
 * @package com.suda.server
 * @date 2019-05-14  14:57
 * @project suda_cloud
 */
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //注入jedis连接工厂对象
    //注入jedis连接工厂对象
    @Autowired
    LettuceConnectionFactory lettuceConnectionFactory;

    @Autowired
    MyConfiguration myConfiguration;

    public void setRedisDbIndex(){
        lettuceConnectionFactory.setDatabase(RedisDbIndexConst.DB_INDEX_1);
    }
    /**
     * 设置hash key
     */
    public void setRedisHash(String redisKey, Long mapKey, String mapValue) {
        setRedisDbIndex();
        redisTemplate.opsForHash().put(redisKey, mapKey, mapValue);
    }

    /**
     * 获取一个hash map
     */
    public Map getRedisHashValue(String redisKey, long mapKey) {
        setRedisDbIndex();
        return (Map) redisTemplate.opsForHash().get(redisKey, mapKey);
    }
    /**
     * 获取所有hash maps
     */
    public Map getRedisHashValues(String redisKey) {
        setRedisDbIndex();
        return  redisTemplate.opsForHash().entries(redisKey);
    }

    /**
     * 删除key
     */
    public void deleteRedisHashKey(String redisKey, long mapKey) {
        setRedisDbIndex();
        redisTemplate.opsForHash().delete(redisKey, mapKey);
    }

    /**
     * 设置短信信息key
     */
    public void setSmsRedisHashValue(Byte codeType,String account,String code){
        setRedisDbIndex();
        redisTemplate.opsForValue().set(
                RedisKeysPrefix.SMS_SEND_TIME_MESSAGE+account+code,code, CommonConstant.VALIDITY_CODE_TIMEOUT, TimeUnit.MILLISECONDS);
        //发送短信时间间隔
        redisTemplate.opsForValue().set(
                RedisKeysPrefix.SMS_SEND_TIME_MESSAGE+account+codeType,code, CommonConstant.VALIDITY_CODE_INTERVAL_TIMEOUT_1, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取单个短信验证码信息
     */
    public Object getSmsRedisMessage(String account,String code){
        setRedisDbIndex();
      return   redisTemplate.opsForValue().get(RedisKeysPrefix.SMS_SEND_TIME_MESSAGE+account+code);
    }
    /**
     * 发送短信时间间隔是否够一分钟
     */
    public Boolean  isCanSendTwoSms(String account,Byte codeType){
        setRedisDbIndex();
        Boolean can = false;
        Object   o = redisTemplate.opsForValue().get(RedisKeysPrefix.SMS_SEND_TIME_MESSAGE+account+codeType);
         if(o==null){
            can=true;
         }
        return can;
    }

    /**
     * 获取单个币种涨跌信息
     */
    public String getStockCodeRealTimeStr(String stockCode){
        lettuceConnectionFactory.setDatabase(RedisDbIndexConst.DB_INDEX_DEFAULT);
        String str = String.valueOf(stringRedisTemplate.opsForValue().get(RedisKeysPrefix.VB_XEX_CHANGE_RATE_DATA+stockCode));
        return str;
    }

    /**
     * 加入队列
     */
    public void lpushQueue(String key,Object object){
        lettuceConnectionFactory.setDatabase(RedisDbIndexConst.DB_INDEX_2);
        stringRedisTemplate.opsForList().leftPush(key,object.toString());
    }

}
