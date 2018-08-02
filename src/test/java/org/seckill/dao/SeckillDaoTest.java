package org.seckill.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring ºÍ junit ÕûºÏ
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {
	
	@Autowired
	private SeckillDao seckillDao;

	@Test
	public void testReduceNumber() {
		Date killTime=new Date();
		long seckillId=1001;
		int res=seckillDao.reduceNumber(seckillId, killTime);
		System.out.println(res);
	}

	@Test
	public void testQueryById() {
		long id =1001;
		Seckill seckill=seckillDao.queryById(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
;	}

	@Test
	public void testQueryAll() {
		
		List<Seckill> res=seckillDao.queryAll(0, 4);
		System.out.println(res);
	}
}
