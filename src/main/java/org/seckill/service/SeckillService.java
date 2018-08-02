package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

public interface SeckillService {
	
	List<Seckill> getSeckillList();
	
	Seckill getById(long id);
	/**
	 * ��ɱ��������url��û��ʼ������ɱʱ��
	 * @param id
	 */
	Exposer exportSeckillUrl(long id);
	
	SeckillExcution excuteSeckill(long seckillId,long userPhone,String md5) 
			throws SeckillException,RepeatKillException,SeckillCloseException;
	
}
