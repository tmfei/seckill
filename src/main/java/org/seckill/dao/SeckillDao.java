/**
 * Project Name:seckill
 * File Name:SeckillDao.java
 * Package Name:org.seckill.dao
 * Date:2016年7月26日上午11:05:23
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
*/

package org.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SecKill;

/**
 * ClassName:SeckillDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年7月26日 上午11:05:23 <br/>
 * @author   wenfei
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface SeckillDao
{
    /**
     * 减库存
     *
     * @author wenfei
     * @param seckillId
     * @param killTime
     * @return 如果影响行数>1，表示更新的记录行数
     * @since JDK 1.7
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
    
    /**
     * 根据id查询秒杀对象
     *
     * @author wenfei
     * @param seckillId
     * @return
     * @since JDK 1.7
     */
    SecKill queryById(long seckillId);
    
    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @author wenfei
     * @param offSet
     * @param limit
     * @return
     * @since JDK 1.7
     */
    List<SecKill> queryAll(@Param("offset") int offSet, @Param("limit") int limit);
    
    /**
     * 使用存储过程执行秒杀过程
     *
     * @author wenfei
     * @param paramMap
     * @since JDK 1.7
     */
    void killByProcedure(Map<String, Object> paramMap);
    
    
    
}

