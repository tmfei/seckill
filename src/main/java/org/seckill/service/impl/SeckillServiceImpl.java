/**
 * Project Name:seckill
 * File Name:SeckillServiceImpl.java
 * Package Name:org.seckill.service.impl
 * Date:2016年8月5日上午9:54:47
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
 */

package org.seckill.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.seckill.dao.RedisDao;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SecKill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * ClassName:SeckillServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月5日 上午9:54:47 <br/>
 * 
 * @author wenfei
 * @version
 * @since JDK 1.7
 * @see
 */
// @Component @Service @Dao @Controller
@Service
public class SeckillServiceImpl implements SeckillService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    // 注入Service依赖
    @Autowired
    private RedisDao redisDao;
    
    @Autowired
    // @Resource
    private SeckillDao seckillDao;
    
    @Autowired
    // @Resource
    private SuccessKilledDao successKillDao;
    
    private final String slat = "jijfefjif2%^#*(%(3diae4pHHIEIJE&!#)&~!@)$)";
    
    @Override
    public List<SecKill> getSeckillList()
    {
        return seckillDao.queryAll(0, 4);
    }
    
    @Override
    public SecKill getById(long seckillId)
    {
        return seckillDao.queryById(seckillId);
    }
    
    @Override
    public Exposer exposeSeckillUrl(long seckillId)
    {
        // 优化点：缓存优化
        // 1:访问redis
        SecKill seckill = null;//redisDao.getSeckill(seckillId);
        if (null == seckill)
        {
            // 2:访问数据库
            seckill = seckillDao.queryById(seckillId);
            
            if (null == seckill)
            {
                return new Exposer(false, seckillId);
            }
//            else
//            {
//                // 3:放入redis
//                redisDao.putSeckill(seckill);
//            }
        }
        
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        
        Date nowTime = new Date();
        if (nowTime.getTime() <= startTime.getTime() || nowTime.getTime() >= endTime.getTime())
        {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        
        // 转换特定字符串的过程，不可逆
        String md5 = getMD5(seckillId);
        
        // TODO Auto-generated method stub
        return new Exposer(true, md5, seckillId);
    }
    
    private String getMD5(long seckillId)
    {
        String base = seckillId + "/" + slat;
        
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        
        return md5;
    }
    
    /**
     * 使用注解控制事务方法的优点： 
     * 1：开发团队达成一致约定，明确标注事务方法的编程风格。 
     * 2：保证事务方法的执行时间尽可能短，不要穿插其他网络操作，比如RPC/HTTP请求，将这些操作剥离到事务方法外部
     * 3：不是所有的方法都需要事务，如只有一条修改操作、只读操作，这些操作不需要事务控制 
     * 看下mysql的行级锁 TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see org.seckill.service.SeckillService#excuteSeckill(long, long, java.lang.String)
     */
    @Override
    @Transactional
    public SeckillExecution excuteSeckill(long seckillId, long userPhone, String md5)
        throws SeckillException, SeckillCloseException, RepeatKillException
    {
        
        if (null == md5 || !md5.equals(getMD5(seckillId)))
        {
            throw new SeckillException("seckill data rewrite");
        }
        
        // 执行秒杀逻辑：减库存 + 记录秒杀行为
        Date nowTime = new Date();
        try
        {
            // 记录购买行为
            int insertCount = successKillDao.insertSuccessKilled(seckillId, userPhone);
            // 唯一：seckillId, userPhone
            if (insertCount <= 0)
            {
                // 重复秒杀
                throw new RepeatKillException("seckill repeat");
            }
            else
            {
                int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
                if (updateCount <= 0)
                {
                    throw new SeckillCloseException("seckill is closed");
                }
                else
                {
                    SuccessKilled successKilled = successKillDao.queryByIdWithSecKill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
            
        }
        catch (SeckillCloseException e1)
        {
            throw e1;
        }
        catch (RepeatKillException e2)
        {
            throw e2;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            // 所有编译期异常，转化为运行期异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
        
    }
    
    @Override
    public SeckillExecution excuteSeckillProcedure(long seckillId, long userPhone, String md5)
    {
        if (null == md5 || !md5.equals(getMD5(seckillId)))
        {
            return new SeckillExecution(seckillId, SeckillStatEnum.DATA_REWRITE);
        }
        
        Date killTime = new Date();
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("seckillId", seckillId);
        paramMap.put("phone", userPhone);
        paramMap.put("killTime", killTime);
        paramMap.put("result", null);
        
        // 执行存储过程，result被赋值
        try
        {
            seckillDao.killByProcedure(paramMap);
            Integer result = MapUtils.getInteger(paramMap, "result", -2);
            if (result == 1)
            {
                SuccessKilled sk = successKillDao.queryByIdWithSecKill(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, sk);
            }
            else
            {
                return new SeckillExecution(seckillId, SeckillStatEnum.stateOf(result));
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
            return new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
        }
        
    }
    
}
