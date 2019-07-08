package config.redis;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;
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
    RedisProperties redisProperties;

    public Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = new Jedis(redisProperties.getHost(),redisProperties.getPort());
            if(redisProperties.getPassword() !=null) {
                jedis.auth(redisProperties.getPassword());
            }
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
}
