package org.likui.study.session1;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.likui.study.redis.session1.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class JedisUtilTest {
    private static final String ip = "127.0.0.1";
    private static final int port = 6379;

    private static Jedis jedis = null;

    @BeforeClass
    public static void init() {
        jedis = JedisUtil.getInstance().getJedis(ip, port);
    }

    @AfterClass
    public static void close() {
        JedisUtil.getInstance().closeJedis(jedis, ip, port);
    }

    @Test
    public void testKey() throws InterruptedException {
        System.out.println("清空数据 : " + jedis.flushDB());
        System.out.println("判断某个键<username>是否存在 : " + jedis.exists("username"));
        System.out.println("新增<'username':'liko'>" + jedis.set("username", "liko"));
        System.out.println("判断某个键<username>是否存在 : " + jedis.exists("username"));
        System.out.println("新增<'password':'123'>" + jedis.set("password", "123"));
        System.out.println("系统中所有的key如下: ");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println("删除键password:" + jedis.del("password"));
        System.out.println("判断键password是否存在：" + jedis.exists("password"));
        System.out.println("设置键username的过期时间为5s:" + jedis.expire("username", 5));
        TimeUnit.SECONDS.sleep(2);
        System.out.println("查看键username的剩余生存时间：" + jedis.ttl("username"));
        System.out.println("移除键username的生存时间：" + jedis.persist("username"));
        System.out.println("查看键username的剩余生存时间：" + jedis.ttl("username"));
        System.out.println("查看键username所存储的值的类型：" + jedis.type("username"));
    }

    @Test
    public void testString() throws InterruptedException {
        jedis.flushDB();
        System.out.println("===========增加数据===========");
        System.out.println(jedis.set("key1", "value1"));
        System.out.println(jedis.set("key2", "value2"));
        System.out.println(jedis.set("key3", "value3"));
        System.out.println("删除键key2:" + jedis.del("key2"));
        System.out.println("获取键key2:" + jedis.get("key2"));
        System.out.println("修改key1:" + jedis.set("key1", "value1Changed"));
        System.out.println("获取key1的值：" + jedis.get("key1"));
        System.out.println("在key3后面加入值：" + jedis.append("key3", "End"));
        System.out.println("key3的值：" + jedis.get("key3"));
        System.out.println("增加多个键值对：" + jedis.mset("key01", "value01", "key02", "value02", "key03", "value03"));
        System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03"));
        System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03", "key04"));
        System.out.println("删除多个键值对：" + jedis.del(new String[]{"key01", "key02"}));
        System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03"));

        jedis.flushDB();
        System.out.println("===========新增键值对防止覆盖原先值==============");
        System.out.println(jedis.setnx("key1", "value1"));
        System.out.println(jedis.setnx("key2", "value2"));
        System.out.println(jedis.setnx("key2", "value2-new"));
        System.out.println(jedis.get("key1"));
        System.out.println(jedis.get("key2"));

        System.out.println("===========新增键值对并设置有效时间=============");
        System.out.println(jedis.setex("key3", 2, "value3"));
        System.out.println(jedis.get("key3"));
        TimeUnit.SECONDS.sleep(3);
        System.out.println(jedis.get("key3"));

        System.out.println("===========获取原值，更新为新值==========");//GETSET is an atomic set this value and return the old value command.
        System.out.println(jedis.getSet("key2", "key2GetSet"));
        System.out.println(jedis.get("key2"));

        System.out.println("获得key2的值的字串：" + jedis.getrange("key2", 2, 4));
    }

    @Test
    public void testNumber() {
        jedis.flushDB();
        jedis.set("key1", "1");
        jedis.set("key2", "2");
        jedis.set("key3", "2.3");
        System.out.println("key1的值：" + jedis.get("key1"));
        System.out.println("key2的值：" + jedis.get("key2"));
        System.out.println("key1的值加1：" + jedis.incr("key1"));
        System.out.println("获取key1的值：" + jedis.get("key1"));
        System.out.println("key2的值减1：" + jedis.decr("key2"));
        System.out.println("获取key2的值：" + jedis.get("key2"));
        System.out.println("将key1的值加上整数5：" + jedis.incrBy("key1", 5));
        System.out.println("获取key1的值：" + jedis.get("key1"));
        System.out.println("将key2的值减去整数5：" + jedis.decrBy("key2", 5));
        System.out.println("获取key2的值：" + jedis.get("key2"));
    }

    @Test
    public void testList() {
        jedis.flushDB();
        System.out.println("===========添加一个list===========");
        jedis.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap", "LinkedHashMap");
        jedis.lpush("collections", "HashSet");
        jedis.lpush("collections", "TreeSet");
        jedis.lpush("collections", "TreeMap");
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));//-1代表倒数第一个元素，-2代表倒数第二个元素
        System.out.println("collections区间0-3的元素：" + jedis.lrange("collections", 0, 3));
        System.out.println("===============================");
        // 删除列表指定的值 ，第二个参数为删除的个数（有重复时），后add进去的值先被删，类似于出栈
        System.out.println("删除指定元素个数：" + jedis.lrem("collections", 2, "HashMap"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
        System.out.println("删除下表0-3区间之外的元素：" + jedis.ltrim("collections", 0, 3));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
        System.out.println("collections列表出栈（左端）：" + jedis.lpop("collections"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
        System.out.println("collections添加元素，从列表右端，与lpush相对应：" + jedis.rpush("collections", "EnumMap"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
        System.out.println("collections列表出栈（右端）：" + jedis.rpop("collections"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
        System.out.println("修改collections指定下标1的内容：" + jedis.lset("collections", 1, "LinkedArrayList"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
        System.out.println("===============================");
        System.out.println("collections的长度：" + jedis.llen("collections"));
        System.out.println("获取collections下标为2的元素：" + jedis.lindex("collections", 2));
        System.out.println("===============================");
        jedis.lpush("sortedList", "3", "6", "2", "0", "7", "4");
        System.out.println("sortedList排序前：" + jedis.lrange("sortedList", 0, -1));
        System.out.println(jedis.sort("sortedList"));
        System.out.println("sortedList排序后：" + jedis.lrange("sortedList", 0, -1));
    }

    @Test
    public void testHash() {
        jedis.flushDB();
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        jedis.hmset("hash", map);
        jedis.hset("hash", "key5", "value5");
        System.out.println("散列hash的所有键值对为:" + jedis.hgetAll("hash"));
        System.out.println("散列hash的所有键为: " + jedis.hkeys("hash"));
        System.out.println("散列hash的所有值为: " + jedis.hvals("hash"));
        System.out.println("将key6保存的值加上一个整数, 如果key6不存在则添加key6 : " + jedis.hincrBy("hash", "key6", 6));
        System.out.println("散列hash的所有键值对为 : " + jedis.hgetAll("hash"));
        System.out.println("将key6保存的值加上一个整数，如果key6不存在则添加key6：" + jedis.hincrBy("hash", "key6", 3));
        System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));
        System.out.println("删除一个或者多个键值对：" + jedis.hdel("hash", "key2"));
        System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));
        System.out.println("散列hash中键值对的个数：" + jedis.hlen("hash"));
        System.out.println("判断hash中是否存在key2：" + jedis.hexists("hash", "key2"));
        System.out.println("判断hash中是否存在key3：" + jedis.hexists("hash", "key3"));
        System.out.println("获取hash中的值：" + jedis.hmget("hash", "key3"));
        System.out.println("获取hash中的值：" + jedis.hmget("hash", "key3", "key4"));
    }

    @Test
    public void testSet() {
        Set<String> set = new HashSet<String>();
        set.add("1");
        set.add("2");
        Object[] objects = set.toArray();
        System.out.println(Arrays.toString(objects));
    }
}
