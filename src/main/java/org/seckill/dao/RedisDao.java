/**
 * Project Name:seckill
 * File Name:RedisDao.java
 * Package Name:org.seckill.dao
 * Date:2016年9月22日下午3:34:33
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
*/

package org.seckill.dao;

import org.seckill.entity.SecKill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * ClassName:RedisDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年9月22日 下午3:34:33 <br/>
 * @author   wenfei
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class RedisDao
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RuntimeSchema<SecKill> schema = RuntimeSchema.createFrom(SecKill.class);
    
    private final JedisPool jedisPool;
    
    public RedisDao(JedisPoolConfig jedisPoolConfig, String ip, int port)
    {
        jedisPool = new JedisPool(jedisPoolConfig, ip, port);
    }
    
    public SecKill getSeckill(long seckillId)
    {
        try
        {
            Jedis jedis = jedisPool.getResource();
            try
            {
                String key = "seckill:" + seckillId;
                //并没有实现内序列化操作
                //get -> byte[] -> 反序列化 -> Object(Seckill)
                //采用自定义序列化
                //protostuff： 被序列话的对象必须是pojo
                byte[] bytes = jedis.get(key.getBytes());
                //缓存中获取到
                if (null != bytes)
                {
                    SecKill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    //seckill被反序列化
                    return seckill;
                }
            }
            finally
            {
                jedisPool.close();
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    
    public String putSeckill(SecKill seckill)
    {
        //set Object(Seckill) -> 序列化-> byte[]
        
        try
        {
            Jedis jedis = jedisPool.getResource();
            try
            {
                String key = "seckill:" + seckill.getSeckillId();
                byte[] bytes =
                    ProtostuffIOUtil.toByteArray(seckill,
                        schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存
                int timeout = 60 * 60;//1小时
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                
                return result;
            }
            finally
            {
                jedis.close();
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    
}

