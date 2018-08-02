package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;
import org.seckill.entity.Seckill;

public interface SeckillDao {
	
	/**
	 * ���ݷ���ֵ�ж��Ƿ�ɹ���Ӱ���ֵ
	 * @param seckillId
	 * @param KillTime
	 * @return
	 */
		int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime")Date killTime);
	/**
	 * ������ѯ
	 * @param seckillId
	 * @return
	 */
		
		Seckill queryById(@Param("seckillId") long seckillId);
		/**
		 * ȫ����ѯ
		 * @param offset
		 * @param limit
		 * @return
		 */
		
		List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
