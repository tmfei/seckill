/**
 * Project Name:seckill
 * File Name:RepeatKillException.java
 * Package Name:org.seckill.exception
 * Date:2016年8月5日上午9:20:34
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
*/

package org.seckill.exception;
/**
 * 重复秒杀异常（运行期异常，不需要手动try catch），spring的声明式事务只接收运行期异常回滚策略
 * Date:     2016年8月5日 上午9:20:34 <br/>
 * @author   wenfei
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class RepeatKillException extends SeckillException
{

    public RepeatKillException(String message, Throwable cause)
    {
        
        super(message, cause);
        // TODO Auto-generated constructor stub
        
    }

    public RepeatKillException(String message)
    {
        
        super(message);
        // TODO Auto-generated constructor stub
        
    }
    
}

