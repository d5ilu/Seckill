package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;

public class SeckillExcution {
	
		private int state;
		
		private String stateInfo;
		
		private long seckillId;
		
		
		private SuccessKilled successKilled;
		
		public SeckillExcution(SeckillStateEnum stateEnum, long seckillId, SuccessKilled successKilled) {
			super();
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateinfo();
			this.seckillId = seckillId;
			this.successKilled = successKilled;
		}

		public SeckillExcution(SeckillStateEnum stateEnum, String stateInfo, long seckillId) {
			super();
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateinfo();
			this.seckillId = seckillId;
		}

		public SeckillExcution(SeckillStateEnum stateEnum, long seckillId) {
			super();
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateinfo();
			this.seckillId = seckillId;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public String getStateInfo() {
			return stateInfo;
		}

		public void setStateInfo(String stateInfo) {
			this.stateInfo = stateInfo;
		}

		public long getSeckillId() {
			return seckillId;
		}

		public void setSeckillId(long seckillId) {
			this.seckillId = seckillId;
		}

		public SuccessKilled getSuccessKilled() {
			return successKilled;
		}

		public void setSuccessKilled(SuccessKilled successKilled) {
			this.successKilled = successKilled;
		}

		
}
