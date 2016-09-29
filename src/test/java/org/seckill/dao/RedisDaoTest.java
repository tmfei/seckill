/**
 * Project Name:seckill
 * File Name:RedisDaoTest.java
 * Package Name:org.seckill.dao
 * Date:2016年9月22日下午7:13:21
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
*/

package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SecKill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * ClassName:RedisDaoTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年9月22日 下午7:13:21 <br/>
 * @author   wenfei
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest
{
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;
    
    @Test
    public void testSeckill()
    {
        long id = 1001;
        SecKill seckill = redisDao.getSeckill(id);
        if (null == seckill)
        {
            seckill = seckillDao.queryById(id);
            if (null != seckill)
            {
                String result = redisDao.putSeckill(seckill);
                System.out.println(result);
                SecKill seckill02 = redisDao.getSeckill(id);
                System.out.println(seckill02);
                
            }
        }
    }
    
    public static void main(String[] args)
    {
     // 生成连接池配置信息
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxTotal(30);
        config.setMaxWaitMillis(3*1000);

        // 在应用初始化的时候生成连接池
//        JedisPool pool = new JedisPool(config, "192.168.0.116", 6379);
        JedisPool pool = new JedisPool("192.168.0.116", 6379);

        // 在业务操作时，从连接池获取连接
        Jedis client = pool.getResource();
        try {
            // 执行指令
            String result = client.set("key-string", "Hello, Redis!");
            System.out.println( String.format("set指令执行结果:%s", result) );
            String value = client.get("key-string");
            System.out.println( String.format("get指令执行结果:%s", value) );
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            pool.close();
        } // end of try block

        // 应用关闭时，释放连接池资源
        pool.destroy();
    }
    
}

