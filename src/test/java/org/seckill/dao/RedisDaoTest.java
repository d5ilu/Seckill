package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class RedisDaoTest {
	
	private long id =1002l;
	@Autowired
	private RedisDao redisDao;
	@Autowired
	private SeckillDao seckillDao;
	@Test
	public void testSeckill() {
		Seckill seckill=redisDao.getSeckill(id);
		System.out.println(seckill);
		if(seckill==null) {
			seckill=seckillDao.queryById(id);
			String result =redisDao.putSeckill(seckill);
			System.out.println(result);
			Seckill seckill2=redisDao.getSeckill(id);
			System.out.println(seckill2);
		}
	}

}
