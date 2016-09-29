/**
 * Project Name:seckill
 * File Name:SeckillException.java
 * Package Name:org.seckill.exception
 * Date:2016年8月5日上午9:32:23
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
*/

package org.seckill.exception;
/**
 * 秒杀异常
 * Date:     2016年8月5日 上午9:32:23 <br/>
 * @author   wenfei
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class SeckillException extends RuntimeException
{
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.7
     */
    private static final long serialVersionUID = -8181529577964661145L;

    public SeckillException(String message, Throwable cause)
    {
        
        super(message, cause);
        // TODO Auto-generated constructor stub
        
    }

    public SeckillException(String message)
    {
        
        super(message);
        // TODO Auto-generated constructor stub
        
    }
    
}

