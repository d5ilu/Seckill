package org.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
	
	/**���빺����ϸ
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

	/**
	 * ������ѯ
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryById(long seckillId);
	
	//List<SuccessKilled> queryAll(int offset,int limit);
}
