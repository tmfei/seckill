/**
 * Project Name:seckill
 * File Name:SeckillService.java
 * Package Name:org.seckill.service
 * Date:2016年8月3日下午2:36:15
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
 */

package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SecKill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

/**
 * 业务接口：站在“使用者”角度设计接口 三个方面：方法定义粒度，参数，返回类型（return类型/异常） ClassName:SeckillService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月3日 下午2:36:15 <br/>
 * 
 * @author wenfei
 * @version
 * @since JDK 1.7
 * @see
 */
public interface SeckillService
{
    /**
     * 查询所有秒杀记录
     * 
     * @return
     * @since JDK 1.7
     */
    List<SecKill> getSeckillList();
    
    /**
     * 查询单个秒杀记录
     * 
     * @param seckillId
     * @return
     * @since JDK 1.7
     */
    SecKill getById(long seckillId);
    
    /**
     * 秒杀开启时，输出秒杀接口地址， 否则输出系统时间和秒杀时间
     * 
     * @param seckillId
     * @since JDK 1.7
     */
    Exposer exposeSeckillUrl(long seckillId);
    
    /**
     * 执行秒杀操作
     *
     * @author wenfei
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws SeckillCloseException
     * @throws RepeatKillException
     * @since JDK 1.7
     */
    SeckillExecution excuteSeckill(long seckillId, long userPhone, String md5)
        throws SeckillException, SeckillCloseException, RepeatKillException;
    
    /**
     * 执行秒杀操作存储过程
     *
     * @author wenfei
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws SeckillCloseException
     * @throws RepeatKillException
     * @since JDK 1.7
     */
    SeckillExecution excuteSeckillProcedure(long seckillId, long userPhone, String md5);
}
