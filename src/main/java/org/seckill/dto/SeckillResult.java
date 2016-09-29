/**
 * Project Name:seckill
 * File Name:SeckillResult.java
 * Package Name:org.seckill.dto
 * Date:2016年9月12日下午3:22:02
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
 */

package org.seckill.dto;

/**
 * ClassName:SeckillResult <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年9月12日 下午3:22:02 <br/>
 * 
 * @author wenfei
 * @version
 * @since JDK 1.7
 * @see
 */
// 所有的ajax请求返回类型，封装json结果
public class SeckillResult<T>
{
    private boolean success;
    
    private T data;
    
    private String error;
    
    public SeckillResult(boolean success, T data)
    {
        super();
        this.success = success;
        this.data = data;
    }
    
    public SeckillResult(boolean success, String error)
    {
        super();
        this.success = success;
        this.error = error;
    }
    
    public boolean isSuccess()
    {
        return success;
    }
    
    public void setSuccess(boolean success)
    {
        this.success = success;
    }
    
    public T getData()
    {
        return data;
    }
    
    public void setData(T data)
    {
        this.data = data;
    }
    
    public String getError()
    {
        return error;
    }
    
    public void setError(String error)
    {
        this.error = error;
    }
    
}
