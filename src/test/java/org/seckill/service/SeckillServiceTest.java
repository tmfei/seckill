/**
 * Project Name:seckill
 * File Name:SeckillServiceTest.java
 * Package Name:org.seckill.service
 * Date:2016年8月7日下午4:38:15
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
 */

package org.seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SecKill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ClassName:SeckillServiceTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月7日 下午4:38:15 <br/>
 * 
 * @author wenfei
 * @version
 * @since JDK 1.7
 * @see
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SeckillService seckillService;
    
    @Test
    public void testGetSeckillList()
    {
        List<SecKill> seckillList = seckillService.getSeckillList();
        logger.info("list={}", seckillList);
    }
    
    @Test
    public void testGetById()
    {
        long id = 1000;
        SecKill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }
    
    @Test
    public void testExposeSeckillUrl()
    {
        long id = 1000;
        Exposer exposeSeckillUrl = seckillService.exposeSeckillUrl(id);
        logger.info("exposer={}", exposeSeckillUrl);
        
        String md5 = exposeSeckillUrl.getMd5();
        long userPhone = 13685246987L;
        
        try
        {
            SeckillExecution excuteSeckill = seckillService.excuteSeckill(id, userPhone, md5);
            logger.info("result={}", excuteSeckill);
        }
        catch (SeckillCloseException e)
        {
            logger.error("SeckillCloseException={}", e.getMessage());
        }
        catch (RepeatKillException e)
        {
            logger.error("RepeatKillException={}", e.getMessage());
        }
        catch (SeckillException e)
        {
            logger.error("SeckillException={}", e.getMessage());
        }
        
    }
    
    @Test
    public void testExcuteSeckillProcedure()
    {
        long seckillId = 1001;
        long phone = 1326876582L;
        Exposer exposeSeckillUrl = seckillService.exposeSeckillUrl(seckillId);
        if (exposeSeckillUrl.isExposed())
        {
            String md5 = exposeSeckillUrl.getMd5();
            SeckillExecution execution = seckillService.excuteSeckillProcedure(seckillId, phone, md5);
            logger.info(execution.getStateInfo());
        }
    }
    
}
