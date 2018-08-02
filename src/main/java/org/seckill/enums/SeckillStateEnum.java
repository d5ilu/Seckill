package org.seckill.enums;

public enum SeckillStateEnum {
	SUCCESS(1,"秒杀成功"),
	END(0,"秒杀结束"),
	REPEAT_KILL(-1,"重复秒杀"),
	INNER_ERROR(-2,"系统错误"),
	DATA_REWRITE(-3,"数据篡改");
	private int state;

	private String stateinfo;

	public int getState() {
		return state;
	}

	public String getStateinfo() {
		return stateinfo;
	}

	private SeckillStateEnum(int state, String stateinfo) {
		this.state = state;
		this.stateinfo = stateinfo;
	}

	public static SeckillStateEnum stateof(int index) {
		for(SeckillStateEnum state:values()) {
			if(state.getState()==index) {
				return state;
			}
		}
		 return null;
	}
	
}
