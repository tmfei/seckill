/**
 * Project Name:seckill
 * File Name:SeckillExecution.java
 * Package Name:org.seckill.dto
 * Date:2016年8月4日上午8:11:58
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
 */

package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;

/**
 * 封装秒杀执行后结果 
 * Date: 2016年8月4日 上午8:11:58 <br/>
 * 
 * @author wenfei
 * @version
 * @since JDK 1.7
 * @see
 */
public class SeckillExecution
{
    private long seckillId;
    
    private int state;
    
    private String stateInfo;
    
    private SuccessKilled successKilled;
    
    public SeckillExecution(long seckillId,SeckillStatEnum statEnum)
    {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
    }

    public SeckillExecution(long seckillId, SeckillStatEnum statEnum, SuccessKilled successKilled)
    {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public long getSeckillId()
    {
        return seckillId;
    }
    
    public void setSeckillId(long seckillId)
    {
        this.seckillId = seckillId;
    }
    
    public int getState()
    {
        return state;
    }
    
    public void setState(int state)
    {
        this.state = state;
    }
    
    public String getStateInfo()
    {
        return stateInfo;
    }
    
    public void setStateInfo(String stateInfo)
    {
        this.stateInfo = stateInfo;
    }
    
    public SuccessKilled getSuccessKilled()
    {
        return successKilled;
    }
    
    public void setSuccessKilled(SuccessKilled successKilled)
    {
        this.successKilled = successKilled;
    }

    @Override
    public String toString()
    {
        return "SeckillExecution [seckillId=" + seckillId + ", state=" + state + ", stateInfo=" + stateInfo
            + ", successKilled=" + successKilled + "]";
    }
    
    
}
