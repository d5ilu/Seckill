package org.seckill.entity;

import java.util.Date;

public class SuccessKilled {
	
	private long seckillId;
	
	private long userPhone ;
	
	private short state;
	
	private Date createTime;
	
	private Seckill seckill;
	
	public SuccessKilled(long seckillId, long userPhone, short state, Date createTime, Seckill seckill) {
		super();
		this.seckillId = seckillId;
		this.userPhone = userPhone;
		this.state = state;
		this.createTime = createTime;
		this.seckill = seckill;
	}

	public long getUserphone() {
		return userPhone;
	}

	public void setUserphone(long userphone) {
		this.userPhone = userphone;
	}

	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long id) {
		this.seckillId = id;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "successKilled{"+"seckillId="+seckillId+",name="+userPhone+",state="+state+",createTime="+createTime+"}";
	}
}
