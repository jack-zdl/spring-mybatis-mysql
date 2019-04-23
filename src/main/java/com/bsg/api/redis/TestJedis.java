package com.bsg.api.redis;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by zhang on 2017/5/2.
 *
 * @description 测试连接redis
 */
public class TestJedis {
    public static ApplicationContext ctx;
    public static JedisConnectionFactory jedisConnetionFactory;
    public JedisConnection jedisConnection;

    //java代码连接redis数据库
    @Test
    public void testOldRedisLink() {
        String HOST_PROPERTY = "47.104.87.10";
        String PORT_PROPERTY = "6379";
        //    String PASSWORD_PROPERTY = "redis.password";
        Jedis jedis = new Jedis(HOST_PROPERTY, Integer.parseInt(PORT_PROPERTY));
        System.out.println(jedis.get("single"));
        jedis.append("single", "test");
        System.out.println(jedis.get("single"));
    }

    @Test
    public void testOldRedisPoolLink() {
        String HOST_PROPERTY = "localhost";
        String PORT_PROPERTY = "6379";
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            // JedisPool依赖于apache-commons-pools1
            pool = new JedisPool(new JedisPoolConfig(), HOST_PROPERTY, Integer.parseInt(PORT_PROPERTY));
            jedis = pool.getResource();
            jedis.set("muti", "bar");
            System.out.println(jedis.get("muti"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis) {
                //释放已经用过的连接
                pool.close();
                pool.destroy();
            }
        }
    }

    @SuppressWarnings("unchecked")
    @BeforeClass
    public static void setBeforeClass() {
        ctx = new ClassPathXmlApplicationContext("classpath:config/spring-redis.xml");
        jedisConnetionFactory = (JedisConnectionFactory) ctx
                .getBean("jedisConnectionFactory");
    }

    @Before
    public void setBefore() {
        jedisConnection = jedisConnetionFactory.getConnection();
    }

    @After
    public void setAfter() {
        jedisConnection.close();
    }

    private void print(Collection<RedisServer> c) {
        for (Iterator<RedisServer> iter = c.iterator(); iter.hasNext(); ) {
            RedisServer rs = (RedisServer) iter.next();
            System.out.println(rs.getHost() + ":" + rs.getPort());
        }
    }

    // 简单测试JedisConnection
    @Ignore
    @Test
    public void test1() {
        if (!jedisConnection.exists(new String("zz").getBytes())) {
            jedisConnection.set(new String("zz").getBytes(),
                    new String("zz").getBytes());
        }
    }

}
