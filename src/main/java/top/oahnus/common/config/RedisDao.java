package top.oahnus.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import top.oahnus.common.utils.ProtoStuffSerializerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by oahnus on 2017/10/3
 * 8:39.
 */
@Configuration
@Slf4j
public class RedisDao {
    @Value("${spring.application.name}")
    private String keyBase;    //key基础

    private JedisPool jedisPool;
    @Value("${spring.redis.host}")
    private String ip;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.maxTotal}")
    private int maxTotal;
    @Value("${spring.redis.maxIdle}")
    private int maxIdle;
    @Value("${spring.redis.waitMill}")
    private long waitMill;
    @Value("${spring.redis.password:null}")
    private String password;

    @Bean
    public RedisDao redisFactory() {
        JedisPoolConfig config = new JedisPoolConfig();
        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxTotal(maxTotal);
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(maxIdle);
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWaitMillis(waitMill);
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(true);

        /**
         * 在将连接放回池中前，自动检验连接是否有效
         */
        config.setTestOnReturn(true);

        /**
         * 自动测试池中的空闲连接是否都是可用连接
         */
        config.setTestWhileIdle(true);

        /**
         * 每次释放连接的最大数目
         */
        config.setNumTestsPerEvictionRun(100);

        /**
         * 释放连接的扫描间隔（毫秒）
         */
        config.setTimeBetweenEvictionRunsMillis(10);

        /**
         * 连接空闲多久后释放(毫秒), 当空闲时间>该值 且 空闲连接>最大空闲连接数 时直接释放
         */
        config.setMinEvictableIdleTimeMillis(100);
        if (ObjectUtils.isEmpty(password)||"null".equals(password)){
            jedisPool = new JedisPool(config, ip, port,100000);
        }else {
            jedisPool = new JedisPool(config, ip, port,100000,password);
        }
        return new RedisDao();
    }

    public <T> T getBean(String key, Class<T> targetClass) {
        try {
            key = keyBase + ":" + key;

            Jedis jedis = getJedis();
            try {
                byte[] bytes = jedis.get(key.getBytes());
//               String bytes =  jedis.get(key);
                if (bytes != null) {
                    T result = ProtoStuffSerializerUtil.deserialize(bytes, targetClass);
//                    T result = JSON.parseObject(bytes,targetClass);
                    return result;
                }
            } finally {
                returnResource(jedis);
            }
        } catch (Exception e) {
            log.error("jedis", e);
        }
        return null;
    }
    public <T> String putBean(String key, T object) {
        return putBean(key, object, 60 * 60);
    }


    public <T> String putBean(String key, T object, Integer timeout) {
        try {
            key = keyBase + ":" + key;

            Jedis jedis = getJedis();
            try {

                if (timeout == null) {
                    //超时缓存
                    timeout = 60 * 60;  //一个小时
                }

                byte[] bytes = ProtoStuffSerializerUtil.serialize(object);
                return jedis.setex(key.getBytes(), timeout, bytes);
            } finally {
                returnResource(jedis);
            }
        } catch (Exception e) {
            log.error("jedis", e);
        }

        return null;
    }


    public <T> List<T> getList(String key, Class cl) {
        try {
            key = keyBase + ":" + key;
            Jedis jedis = getJedis();
            try {
                byte[] bytes = jedis.get(key.getBytes());
//                String bytes =  jedis.get(key);
                if (bytes != null) {
                    if ("[]".equals(bytes)) {
                        return new ArrayList<>();
                    }
                    List result = ProtoStuffSerializerUtil.deserializeList(bytes, cl);
//                    List result = (List) JSON.parseObject(bytes,cl);
                    return result;
                }
            } finally {
                returnResource(jedis);
            }
        } catch (Exception e) {
            log.error("jedis", e);
        }
        return null;
    }

    public <T> String putList(String key, List list) {
        return putList(key, list, 60 * 60);
    }


    public String putList(String key, List list, Integer timeout) {
        try {
            key = keyBase + ":" + key;
            Jedis jedis = getJedis();
            try {
                byte[] bytes = ProtoStuffSerializerUtil.serializeList(list);
//                String bytes = JSON.toJSONString(list);
                if (timeout == null) {
                    //超时缓存
                    timeout = 60 * 60;  //一个小时
                }
                String result = jedis.setex(key.getBytes(), timeout, bytes);
//                String result = jedis.setex(key,timeout,bytes);
                return result;
            } finally {
                returnResource(jedis);
            }
        } catch (Exception e) {
            log.error("jedis", e);
        }

        return null;
    }

    public void delete(String key) {
        key = keyBase + ":" + key;
        Jedis jedis = getJedis();
        try {
            Set keys = jedis.keys(key.toString());
            int size = keys.size();
            String[] result = (String[])keys.toArray(new String[size]);//使用了第二种接口，返回值和参数均为结果
            if (!ObjectUtils.isEmpty(result)){
                jedis.del(result);  //删除某个键
            }
        } finally {
            returnResource(jedis);
        }
    }


    /**
     * 获取Jedis实例
     *
     * @return
     */
    public synchronized Jedis getJedis() {
        if (jedisPool != null) {
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                return jedis;
            } catch (Exception e) {
                returnResource(jedis);
                log.error("Get jedis error : "+e);
            }
        }
        return null;
    }

    private void returnResource(final Jedis jedis) {
        if (jedis != null && jedisPool !=null) {
            jedis.close();
        }
    }
}
