package config.redis;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @author 卫星
 * @package config.redis
 * @date 2019-04-20  01:38
 * @project niuwan_cloud
 */
@Configuration
public class JedisManager {

    @Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;

    public Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = (Jedis) lettuceConnectionFactory.getConnection().getNativeConnection();
        } catch (JedisConnectionException e) {
            String message = StringUtils.trim(e.getMessage());
            if ("Could not get a resource from the pool".equalsIgnoreCase(message)) {
                System.out.println("++++++++++请检查你的redis服务++++++++");
                System.out.println("项目退出中....生产环境中，请删除这些东西。我来自。JedisManage.java line:53");
                System.exit(0);//停止项目
            }
            throw new JedisConnectionException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jedis;
    }

    public LettuceConnectionFactory getRedisConnectionFactory() {
        return lettuceConnectionFactory;
    }

    public void setRedisConnectionFactory(LettuceConnectionFactory redisConnectionFactory) {
        this.lettuceConnectionFactory = redisConnectionFactory;
    }

    public void returnResource(Jedis jedis, boolean isBroken) {
        if (jedis == null) {
            return;
        }
        jedis.close();
    }

    public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        byte[] result = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
        return result;
    }

    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            Long result = jedis.del(key);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime)
            throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.set(key, value);
            if (expireTime > 0)
                jedis.expire(key, expireTime);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

}
