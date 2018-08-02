package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;
import org.seckill.entity.Seckill;

public interface SeckillDao {
	
	/**
	 * 根据返回值判断是否成功，影响的值
	 * @param seckillId
	 * @param KillTime
	 * @return
	 */
		int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime")Date killTime);
	/**
	 * 单条查询
	 * @param seckillId
	 * @return
	 */
		
		Seckill queryById(@Param("seckillId") long seckillId);
		/**
		 * 全部查询
		 * @param offset
		 * @param limit
		 * @return
		 */
		
		List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
