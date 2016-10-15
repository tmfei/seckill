/**
 * Project Name:seckill
 * File Name:SeckillStatEnum.java
 * Package Name:org.seckill.enums
 * Date:2016年8月5日上午10:33:08
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
 */

package org.seckill.enums;

/**
 * 使用枚举表述常量数据字典 Date: 2016年8月5日 上午10:33:08 <br/>
 * 
 * @author wenfei
 * @version
 * @since JDK 1.7
 * @see
 */
public enum SeckillStatEnum
{
    SUCCESS(1, "秒杀成功"), END(0, "秒杀结束"), REPEAT_KILL(-1, "重复秒杀"), INNER_ERROR(-2, "系统异常"), DATA_REWRITE(-3, "数据篡改");
    
    private int state;
    
    private String stateInfo;
    
    private SeckillStatEnum(int state, String stateInfo)
    {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    
    public int getState()
    {
        return state;
    }
    
    public String getStateInfo()
    {
        return stateInfo;
    }
    
    public static SeckillStatEnum stateOf(int index)
    {
        for (SeckillStatEnum state : values())
        {
            if (state.getState() == index)
            {
                return state;
            }
        }
        
        return null;
    }
    
}
