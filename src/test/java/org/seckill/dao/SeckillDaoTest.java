/**
 * Project Name:seckill
 * File Name:SeckillDaoTest.java
 * Package Name:org.seckill.dao
 * Date:2016年7月30日下午5:12:55
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
*/

package org.seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SecKill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ClassName:SeckillDaoTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年7月30日 下午5:12:55 <br/>
 * @author   wenfei
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */

/**
 * 配置spring和junit整合，是为了junit启动时加载spring ioc容器，使用@RunWith(SpringJUnit4ClassRunner.class)
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest
{
    //注入dao实现类依赖
    @Resource  //这个注释会自动到spring容器中查找seckillDao的实现类，然后注入到单元测试类里面
    private SeckillDao seckillDao;
    
    @Test
    public void testQueryById()
    {
        long id = 1000;
        SecKill secKill = seckillDao.queryById(id);
        System.out.println(secKill.getName());
        System.out.println(secKill);
        
    }
    
    @Test
    public void testReduceNumber()
    {
        long id = 1000;
        Date killTime = new Date();
        int n = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("成功见库存：" + id + "，减的数量：" + n);
    }
    
    
    @Test
    public void testQueryAll()
    {
        List<SecKill> seckills = seckillDao.queryAll(0, 100);
        for (SecKill secKill : seckills)
        {
            System.out.println(secKill.getName());
        }
    }
    
}

