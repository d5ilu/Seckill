package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.seckill.dao.RedisDao;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import ch.qos.logback.classic.Logger;
@Service
public class SeckillServiceImpl implements SeckillService{

	private Logger logger=(Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisDao redisDao;

	@Autowired
	private SeckillDao seckillDao;

	@Autowired
	private SuccessKilledDao successKilledDao;

	private final String slat="528822skdkadkshj";

	public List<Seckill> getSeckillList() {
		// TODO Auto-generated method stub
		return seckillDao.queryAll(0, 4);
	}

	public Seckill getById(long id) {
		// TODO Auto-generated method stub
		return seckillDao.queryById(id);
	}

	public Exposer exportSeckillUrl(long id) {
		// TODO Auto-generated method stub
		/**缓存优化
		 * 使用redis
		 */
		Seckill seckill=redisDao.getSeckill(id);
		if(seckill==null) {
			seckill=seckillDao.queryById(id);
			if(seckill==null) {
				return new Exposer(false,id);
			}
			else {
				redisDao.putSeckill(seckill);
			}
		}
		Date startTime=seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime= new Date();
		if(nowTime.getTime()>endTime.getTime() 
				|| nowTime.getTime()<startTime.getTime())
			return new Exposer(false,nowTime.getTime(),startTime.getTime(),endTime.getTime());
		String md5=getMd5(id);
		return new Exposer(true, md5,id);
	}
	@Transactional
	public SeckillExcution excuteSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		// TODO Auto-generated method stub
		if(md5==null||!md5.equals(getMd5(seckillId)))
			throw new SeckillException("seckill data rewrite");
		Date nowTime=new Date();
		try {
			int insertCount=successKilledDao.insertSuccessKilled(seckillId, userPhone);
			if(insertCount<=0)
				throw new RepeatKillException("seckill repeated");
			else {
				int update=seckillDao.reduceNumber(seckillId, nowTime);
				if(update<=0) {
					throw new SeckillCloseException("seckill is closed");
				}
				else {
					Seckill seckill= seckillDao.queryById(seckillId);
					SuccessKilled successKilled =new SuccessKilled(seckillId, userPhone, (short)1, new Date(), seckill);
					return new SeckillExcution(SeckillStateEnum.SUCCESS ,seckillId,successKilled);
				}
			}
		}catch(SeckillCloseException e1){
			throw e1;
		} catch(RepeatKillException e1){
			throw e1;
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
			throw new SeckillException("seckill inner error"+e.getMessage());
		}


	}
	private String getMd5(long seckillId) {
		String base=seckillId+"/"+slat;
		String md5=DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;

	}

}
