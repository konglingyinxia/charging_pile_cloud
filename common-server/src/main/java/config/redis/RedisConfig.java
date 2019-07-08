package config.redis;

import com.constant.RedisDbIndexConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 卫星
 * @package config.redis
 * @date 2019-04-20  01:42
 * @project niuwan_cloud
 */
@Configuration
public class RedisConfig {

    @Autowired
    RedisProperties redisProperties;

    @Bean
    JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig poolConfig = new  JedisPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getLettuce().getPool().getMaxIdle());
        poolConfig.setMaxTotal(redisProperties.getLettuce().getPool().getMaxActive());
        poolConfig.setMinIdle(redisProperties.getLettuce().getPool().getMinIdle());
        poolConfig.setMaxWaitMillis(redisProperties.getLettuce().getPool().getMaxWait().toMillis());
        return poolConfig;

    }

    @Bean
    @Primary
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration ();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        redisStandaloneConfiguration.setDatabase(RedisDbIndexConst.DB_INDEX_DEFAULT);
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();

        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration,
                jedisClientConfiguration.usePooling().poolConfig(jedisPoolConfig()).build());
        return factory;
    }
    @Bean
    JedisConnectionFactory jedisConnectionFactory1() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration ();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        redisStandaloneConfiguration.setDatabase(RedisDbIndexConst.DB_INDEX_1);
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();

        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration,
                jedisClientConfiguration.usePooling().poolConfig(jedisPoolConfig()).build());
        return factory;
    }
    @Bean
    JedisConnectionFactory jedisConnectionFactory2() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration ();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        redisStandaloneConfiguration.setDatabase(RedisDbIndexConst.DB_INDEX_2);
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();

        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration,
                jedisClientConfiguration.usePooling().poolConfig(jedisPoolConfig()).build());
        return factory;
    }
    @Bean
    JedisConnectionFactory jedisConnectionFactory3() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration ();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        redisStandaloneConfiguration.setDatabase(RedisDbIndexConst.DB_INDEX_3);
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();

        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration,
                jedisClientConfiguration.usePooling().poolConfig(jedisPoolConfig()).build());
        return factory;
    }

    @Bean(name = "redisTemplate")
    @Primary
    public RedisTemplate<String, Object> redisTemplate() throws Exception {
        RedisTemplate<String, Object> redisTemplateObject = new RedisTemplate<String, Object>();
        redisTemplateObject.setConnectionFactory(jedisConnectionFactory());
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }

    @Bean(name = "redisTemplate1")
    public RedisTemplate<String, Object> redisTemplate1() throws Exception {
        RedisTemplate<String, Object> redisTemplateObject = new RedisTemplate<String, Object>();
        redisTemplateObject.setConnectionFactory(jedisConnectionFactory1());
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }

    @Bean(name = "redisTemplate2")
    public RedisTemplate<String, Object> redisTemplate2() throws Exception {
        RedisTemplate<String, Object> redisTemplateObject = new RedisTemplate<String, Object>();
        redisTemplateObject.setConnectionFactory(jedisConnectionFactory2());
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }

    @Bean(name = "redisTemplate3")
    public RedisTemplate<String, Object> redisTemplate3() throws Exception {
        RedisTemplate<String, Object> redisTemplateObject = new RedisTemplate<String, Object>();
        redisTemplateObject.setConnectionFactory(jedisConnectionFactory3());
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }

    @Bean(name = "stringRedisTemplate")
    @Primary
    public StringRedisTemplate stringRedisTemplate() throws Exception {
        StringRedisTemplate redisTemplateObject = new StringRedisTemplate();
        redisTemplateObject.setConnectionFactory(jedisConnectionFactory());
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }


    @Bean(name = "stringRedisTemplate1")
    public StringRedisTemplate stringRedisTemplate1() throws Exception {
        StringRedisTemplate redisTemplateObject = new StringRedisTemplate();
        redisTemplateObject.setConnectionFactory(jedisConnectionFactory1());
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }

    @Bean(name = "stringRedisTemplate2")
    public StringRedisTemplate stringRedisTemplate2() throws Exception {
        StringRedisTemplate redisTemplateObject = new StringRedisTemplate();
        redisTemplateObject.setConnectionFactory(jedisConnectionFactory2());
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }

    @Bean(name = "stringRedisTemplate3")
    public StringRedisTemplate stringRedisTemplate3() throws Exception {
        StringRedisTemplate redisTemplateObject = new StringRedisTemplate();
        redisTemplateObject.setConnectionFactory(jedisConnectionFactory3());
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }

}
