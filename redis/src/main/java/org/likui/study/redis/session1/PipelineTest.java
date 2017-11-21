package org.likui.study.redis.session1;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * 利用pipeline的方式从client打包多条命令一起发出，不需要等待单条命令的响应返回，
 * 而redis服务端会处理完多条命令后会将多条命令的处理结果打包到一起返回给客户端。所以pipeline适合批处理作业可以提升效率
 */
public class PipelineTest {
    @Test
    public void testMGet() {
        Jedis jedis = JedisUtil.getInstance().getJedis("127.0.0.1", 6379);
    }
}
