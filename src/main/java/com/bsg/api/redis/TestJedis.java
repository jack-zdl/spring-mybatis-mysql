package com.bsg.api.redis;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

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
