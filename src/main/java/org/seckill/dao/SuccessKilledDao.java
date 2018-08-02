package org.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
	
	/**插入购买明细
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

	/**
	 * 单条查询
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryById(long seckillId);
	
	//List<SuccessKilled> queryAll(int offset,int limit);
}
