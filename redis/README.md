### Redis

##### 常用命令   

1. 连接操作命令
    - quit : 关闭连接
    - auth ： 简单密码认证
    - help cmd ： 查看cmd帮助, 例如 help quit

2. 持久化
    - sava : 将数据同步保存到磁盘
    - bgsave : 将数据异步保存到磁盘
    - lastsava : 返回上次成功将数据保存到磁盘的Unix时间戳
    - shutdown : 将数据同步到磁盘, 然后关闭服务
    
3. 远程服务控制
    - info : 提供服务器的信息和统计
    - monitor : 实时转储收到的信息
    - saveof : 改变复制策略设置
    - config : 在运行时配置Redis服务器
   
4. 对key操作的命令
    - exists(key) : 确认一个key是否存在
    - del(key) : 删除一个key
    - type(key) : 返回值的类型
    - keys(pattern) : 返回满足给定pattern的所有key
    - randomkey : 随机返回key空间的一个
    - keyrename(oldname, newname) : 重命名key
    - dbsize : 返回当前数据库中key的数目
    - expire : 设定一个key的活动时间(s)
    - ttl : 获取一个key剩余的活动时间
    - select(index) : 按索引查询(*)
    - move(key, dbindex) : 移动当前数据库中的key到dbindex数据库
    - flushdb : 删除当前选择数据库中的所有key
    - flushall : 删除所有数据库中的所有key
    
5. String
    - set(key, value) : 给数据库中名称为key的string赋予值value
    - get(key) : 返回数据库中名称为key的string的value
    - getset(key, value) : 将给定 key 的值设为 value ，并返回 key 的旧值(old value), 当 key 存在但不是字符串类型时，返回一个错误。
    - mget(key1, key2, ... , keyn) : 返回数据库中多个string的value
    - setnx(key, value) : set not exists
    - setex(key, time, value) : 设置新值, 并添加超时时间
   
...

> 参见[http://doc.redisfans.com/index.html]