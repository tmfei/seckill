/**
 * Project Name:seckill
 * File Name:SuccessKilledDaoTest.java
 * Package Name:org.seckill.dao
 * Date:2016年7月31日下午3:14:20
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
*/

package org.seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SecKill;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ClassName:SuccessKilledDaoTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年7月31日 下午3:14:20 <br/>
 * @author   wenfei
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest
{
    @Resource
    private SuccessKilledDao successKilledDao;
    
    @Test
    public void testInsertSuccessKilled()
    {
        long id = 1001L;
        long userPhone = 13758948523L;
        int insertSuccessKilled = successKilledDao.insertSuccessKilled(id, userPhone);
        System.out.println("插入成功数量：" + insertSuccessKilled);
    }
    
    @Test
    public void testQueryByIdWithSecKill()
    {
        long id = 1000L;
        long userPhone = 13758948523L;
        SuccessKilled queryByIdWithSecKill = successKilledDao.queryByIdWithSecKill(id, userPhone);
        SecKill secKill = queryByIdWithSecKill.getSecKill();
        
        System.out.println(secKill.toString());
    }
    
}

