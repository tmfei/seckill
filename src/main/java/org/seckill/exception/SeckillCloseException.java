/**
 * Project Name:seckill
 * File Name:SeckillCloseException.java
 * Package Name:org.seckill.exception
 * Date:2016年8月5日上午9:29:51
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
*/

package org.seckill.exception;
/**
 * 秒杀关闭异常
 * Date:     2016年8月5日 上午9:29:51 <br/>
 * @author   wenfei
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class SeckillCloseException extends SeckillException
{

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.7
     */
    private static final long serialVersionUID = -6290425115435104747L;

    public SeckillCloseException(String message, Throwable cause)
    {
        
        super(message, cause);
        // TODO Auto-generated constructor stub
        
    }

    public SeckillCloseException(String message)
    {
        
        super(message);
        // TODO Auto-generated constructor stub
        
    }
    
}

