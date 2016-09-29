/**
 * Project Name:seckill
 * File Name:SecKill.java
 * Package Name:org.seckill.entity
 * Date:2016年7月26日上午10:58:31
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
*/

package org.seckill.entity;

import java.util.Date;

/**
 * ClassName:SecKill <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年7月26日 上午10:58:31 <br/>
 * @author   wenfei
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class SecKill
{
    private long seckillId;
    private String name;
    private int number;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    
    public long getSeckillId()
    {
        return seckillId;
    }
    public void setSeckillId(long seckillId)
    {
        this.seckillId = seckillId;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getNumber()
    {
        return number;
    }
    public void setNumber(int number)
    {
        this.number = number;
    }
    public Date getStartTime()
    {
        return startTime;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }
    public Date getEndTime()
    {
        return endTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }
    public Date getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
//    @Override
//    public String toString()
//    {
//        return "[seckillId=" + seckillId + ", name=" + name + ", number=" + number + ", startTime=" + startTime
//            + ", endTime=" + endTime + ", createTime=" + createTime + "]";
//    }
    
//    @Override
//    public String toString()
//    {
//        return "name=" + name + ", number=" + number;
//        // TODO Auto-generated method stub
////        return super.toString();
//    }
    
}

