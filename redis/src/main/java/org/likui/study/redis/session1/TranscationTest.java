package org.likui.study.redis.session1;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

public class TranscationTest {
    @Test
    public void testWatch() {
        Jedis jedis = JedisUtil.getInstance().getJedis("127.0.0.1", 6379);
        String watch = jedis.watch("testabcd");
        System.out.println(Thread.currentThread().getName() + "--" + watch);
        Transaction multi = jedis.multi();
        multi.set("testabcd", "23432");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Object> exec = multi.exec();
        System.out.println("---" + exec);
        jedis.unwatch();
    }
}
