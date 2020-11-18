package org.likui.study.redis.session1;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class HelloWorld {
    private Jedis jedis = null;

    @Before
    public void before() {
        jedis = new Jedis("127.0.0.1");
    }

    /**
     * 测试连通性
     */
    @Test
    public void test1() {
        System.out.println(jedis.ping());
    }

    /**
     * 操作字符串, 设置&获取key-value
     */
    @Test
    public void setValue() {
        jedis.set("name", "liko");
        System.out.println(jedis.get("name"));
    }

    /**
     * 操作列表
     */
    @Test
    public void testList() {
        jedis.lpush("nblist", "jj");
        jedis.lpush("nblist", "jj");
        jedis.lpush("nblist", "yy");
        jedis.lpush("nblist", "qq");
        List<String> nblist = jedis.lrange("nblist", 0, -1);
        for (int i = 0; i < nblist.size(); i++) {
            System.out.println(nblist.get(i));
        }
    }

    /**
     * 获取所有键
     */
    @Test
    public void testKeys() {
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
    }
}
