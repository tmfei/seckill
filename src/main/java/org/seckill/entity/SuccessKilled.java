/**
 * Project Name:seckill
 * File Name:SuccessKilled.java
 * Package Name:org.seckill.entity
 * Date:2016年7月26日上午11:01:50
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
 */

package org.seckill.entity;

import java.util.Date;

/**
 * ClassName:SuccessKilled <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年7月26日 上午11:01:50 <br/>
 * 
 * @author wenfei
 * @version
 * @since JDK 1.7
 * @see
 */
public class SuccessKilled
{
    private long seckillId;
    
    private long userPhone;
    
    private short state;
    
    private Date createTime;
    
    //变通
    //多对一
    private SecKill secKill;
    
    public long getSeckillId()
    {
        return seckillId;
    }
    
    public void setSeckillId(long seckillId)
    {
        this.seckillId = seckillId;
    }
    
    public long getUserPhone()
    {
        return userPhone;
    }
    
    public void setUserPhone(long userPhone)
    {
        this.userPhone = userPhone;
    }
    
    public short getState()
    {
        return state;
    }
    
    public void setState(short state)
    {
        this.state = state;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public SecKill getSecKill()
    {
        return secKill;
    }

    public void setSecKill(SecKill secKill)
    {
        this.secKill = secKill;
    }

    @Override
    public String toString()
    {
        
        // TODO Auto-generated method stub
        return super.toString();
    }
}
