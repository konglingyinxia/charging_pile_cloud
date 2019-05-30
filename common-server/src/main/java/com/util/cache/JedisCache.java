package com.util.cache;

import com.alibaba.fastjson.JSONObject;
import config.redis.JedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;

/**
 * @author zhengliangzhang
 */
@Configuration
public class JedisCache {
    private static JedisManager jedisManager;
    public static final String EXPX_EX = "EX";
    public static final String EXPX_PX = "PX";
    public static final String NXXX_NX = "NX";
    public static final String NXXX_XX = "XX";
    public static final int defaultIndex = 0;

    public static final int dbIndex_1 = 1;
    public static final int dbIndex_2 = 2;

    @Autowired
    private JedisManager jedisManagerTemp;
    @PostConstruct
    private void init(){
        jedisManager = getJedisManagerTemp();
    }


    public static final Logger LOGGER = LoggerFactory.getLogger(JedisCache.class);


    public JedisManager getJedisManagerTemp() {
        return jedisManagerTemp;
    }

    public void setJedisManagerTemp(JedisManager jedisManagerTemp) {
        this.jedisManagerTemp = jedisManagerTemp;
    }

    public static Set<String> getAll(String pattern) {
        return getAll(defaultIndex, pattern);
    }

    public static Set<String> getAll(int dbIndex, String pattern) {
        Set<String> result = null;
        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = jedis.keys(pattern);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }
        return result;
    }

    public static long delete(String key) {
        return delete(defaultIndex, key);
    }

    public static long delete(int dbIndex, String key) {
        long result = -1;
        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = jedis.del(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }

        return result;
    }


    public static String getStr(String key) {
        return getStr(defaultIndex, key);
    }

    public static String getStr(int dbIndex, String key) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }
        return result;
    }

    public static String setStr(String key, String value) {
        return setStr(defaultIndex, key, value);
    }

    public static String setStr(int dbIndex, String key, String value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = jedis.set(key, value);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }

        return result;
    }

    public static String setStrex(int dbIndex, String key, String value, int seconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = jedis.setex(key, seconds, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

        return result;
    }

    /**
     * 向set中添加元素
     *
     * @param key
     * @param value
     * @return
     */
    public static String addSetValue(String key, String value) {
        return addSetValue(defaultIndex, key, value);
    }

    /**
     * 向set中添加元素
     *
     * @param dbIndex
     * @param key
     * @param value
     * @return
     */
    public static String addSetValue(int dbIndex, String key, String value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = String.valueOf(jedis.sadd(key, value));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }

        return result;
    }

    /**
     * 获取集合
     *
     * @param key
     * @return
     */
    public static Set<String> getSetValue(String key) {
        return getSetValue(defaultIndex, key);
    }

    /**
     * 获取集合
     *
     * @param dbIndex
     * @param key
     * @return
     */
    public static Set<String> getSetValue(int dbIndex, String key) {
        Set<String> result = null;

        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = jedis.smembers(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }
        return result;
    }

    /**
     * 集合中是否存在某种元素
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean existInSet(String key, String value) {
        return existInSet(defaultIndex, key, value);
    }

    /**
     * 集合中是否存在某种元素
     *
     * @param dbIndex
     * @param key
     * @param value
     * @return
     */
    public static boolean existInSet(int dbIndex, String key, String value) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = jedis.sismember(key, value);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }
        return result;
    }


    public static Map<String, String> getHash(String key) {
        return getHash(defaultIndex, key);
    }

    public static Map<String, String> getHash(int dbIndex, String key) {
        Map<String, String> result = null;

        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = jedis.hgetAll(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }
        return result;
    }

    public static String getHashValue(String key, String field) {
        return getHashValue(defaultIndex, key, field);
    }


    public static String getHashValue(int dbIndex, String key, String field) {
        String result = null;

        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = jedis.hget(key, field);
        } catch (Exception e) {
        } finally {
            jedis.close();
        }
        return result;
    }

    public static String setHashObjectValue(String key, String field, Object value) {
        return setHashObjectValue(defaultIndex, key, field, value);
    }

    public static String setHashObjectValue(int dbIndex, String key, String field, Object value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = jedis.hset(key, field, JSONObject.toJSONString(value)).toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }

        return result;
    }

    public static String deleteHashValue(String key, String field) {
        return deleteHashValue(defaultIndex, key, field);
    }

    public static String deleteHashValue(int dbIndex, String key, String field) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);

            result = jedis.hdel(key, field).toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }

        return result;
    }

    public static Object getHashObjectValue(String key, String field, Class clz) {
        return getHashObjectValue(defaultIndex, key, field, clz);
    }

    public static Object getHashObjectValue(int dbIndex, String key, String field, Class clz) {
        Object result = null;
        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);

            result = JSONObject.parseObject(jedis.hget(key, field), clz);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }

        return result;
    }

    public static String setHashValue(String key, String field, String value) {
        return setHashValue(defaultIndex, key, field, value);
    }

    public static String setHashValue(int dbIndex, String key, String field, String value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = jedis.hset(key, field, value).toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }

        return result;
    }


    /**
     * 设置超时时间
     *
     * @param key
     * @param unixTime
     * @return
     */
    public static String expireAt(String key, int unixTime) {
        return expireAt(defaultIndex, key, unixTime);
    }


    /**
     * 设置超时时间
     *
     * @param dbIndex
     * @param key
     * @param unixTime
     * @return
     */
    public static String expireAt(int dbIndex, String key, int unixTime) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = String.valueOf(jedis.expireAt(key, unixTime));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }

        return result;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param seconds
     * @return
     */
    public static String expire(String key, int seconds) {
        return expire(defaultIndex, key, seconds);
    }

    /**
     * 设置过期时间
     *
     * @param dbIndex
     * @param key
     * @param seconds
     * @return
     */
    public static String expire(int dbIndex, String key, int seconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisManager.getJedis();
            jedis.select(dbIndex);
            result = String.valueOf(jedis.expire(key, seconds));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            jedis.close();
        }

        return result;
    }

    public static void deleteSetValue(String sessionId) {
    }
}
