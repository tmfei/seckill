/**
 * Project Name:seckill
 * File Name:SuccessKilledDao.java
 * Package Name:org.seckill.dao
 * Date:2016年7月26日上午11:09:08
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
*/

package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * ClassName:SuccessKilledDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年7月26日 上午11:09:08 <br/>
 * @author   wenfei
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface SuccessKilledDao
{
    /**
     * 插入购买明细，可过滤重复
     *
     * @author wenfei
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     * @since JDK 1.7
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
    
    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     *
     * @author wenfei
     * @param seckillId
     * @return
     * @since JDK 1.7
     */
    SuccessKilled queryByIdWithSecKill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}

