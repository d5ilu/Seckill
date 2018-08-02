package org.seckill.dao;

import org.seckill.entity.Seckill;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import ch.qos.logback.classic.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {

	private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	private JedisPool jedisPool;

	private RuntimeSchema<Seckill> schema=RuntimeSchema.createFrom(Seckill.class);

	public RedisDao(String ip,int port) {
		super();
		jedisPool = new JedisPool(ip, port);
	}

	public Seckill getSeckill(long seckillId) {
		try {
			Jedis jedis= jedisPool.getResource();
			try {
				String key="seckill"+seckillId;
				//没有实现内部序列化
				//getByte[]--反序列化--Object（seckill)
				//使用protostuff:pojo自动以序列化
				byte[] bytes=jedis.get(key.getBytes());
				if(bytes!=null) {
					Seckill seckill=schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
					//反序列
					return seckill;
				}
			}
			finally {
				jedis.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	public String putSeckill(Seckill seckill) {
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckill" + seckill.getSeckillId();
				byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
						LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				int timeout = 60 * 60;
				String result =jedis.setex(key.getBytes(), timeout, bytes);
				return result;
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}
		return null;

	}
}
