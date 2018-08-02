package org.seckill.entity;

import java.util.Date;


public class Seckill {

		private long seckillId;
		
		private String name;
		
		private int number;
		
		private Date startTime;
		
		private Date endTime;
		
		private Date createTime;

		public long getSeckillId() {
			return seckillId;
		}

		public void setSeckillId(long id) {
			this.seckillId = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public Date getStartTime() {
			return startTime;
		}

		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date creatTime) {
			this.createTime = creatTime;
		}
		@Override
		public String toString() {
			return "seckill{"+"id="+seckillId+",name="+name+",number="+number+",startTime="+startTime+",endTime="+endTime+"}";
		}
}
