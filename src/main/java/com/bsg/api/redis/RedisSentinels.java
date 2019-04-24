package com.bsg.api.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhang on 2017/5/16. sentinels 是redis自带的哨兵，提供redis的主从切换
 */
public class RedisSentinels {
    public static final String host = "47.104.87.10";
    public static final String port = "26379";
    public static final String password = "redis_6379";

    /**
     * @description 测试单个连接
     */
    @Test
    public void testSentinels() {
        Set sentinels = new HashSet();
        sentinels.add(new HostAndPort(host, Integer.parseInt(port)).toString());
        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);
        System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());

        Jedis master = sentinelPool.getResource();
//        master.auth(password);

        master.set("username", "killxioahuang");
        sentinelPool.returnResource(master);

        Jedis master2 = sentinelPool.getResource();
        master2.auth(password);
        String value = master2.get("username");
        System.out.println("username: " + value);
        master2.close();
        sentinelPool.destroy();
    }

}
