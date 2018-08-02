package org.seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.classic.Logger;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class SeckillServiceTest {
	
	private final Logger logger=(Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	@Test
	public void testGetSeckillList() {
		List<Seckill> list=seckillService.getSeckillList();
		logger.info("list={}",list);
	}

	@Test
	public void testGetById() {
		Seckill seckill=seckillService.getById(1002);
		logger.info("seckill={}",seckill);
	}

	@Test
	public void testExportSeckillUrl() {
		long id=1002;
		Exposer ex= seckillService.exportSeckillUrl(id);
		logger.info("ex={}",ex);
	}

	@Test
	public void testExcuteSeckill() {
		long id=1002;
		long userPhone=12345678999l;
		String md5="fea7e5142fdb147f2d4f94d30e6539f2";
		SeckillExcution se= seckillService.excuteSeckill(id, userPhone, md5);
		logger.info("se={}",se);
	}

}
