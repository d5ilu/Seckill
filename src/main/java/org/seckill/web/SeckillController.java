package org.seckill.web;

import java.util.Date;
import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
	
	private final Logger logger=(Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillService seckillService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> list=seckillService.getSeckillList();
		model.addAttribute("list",list);
		return "list";
	} 
	
	@RequestMapping(value="/{seckillId}/detail",method=RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long id,Model model) {
		if(id==null) {
			return "rediect:/seckill/list";
		}
		Seckill seckill=seckillService.getById(id);
		if(seckill==null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill",seckill);
		return "detail";
	}
	
	@RequestMapping(value="/{seckillId}/exposer",
					method=RequestMethod.POST,
					produces= {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId")Long seckillId) {
		SeckillResult<Exposer> result;
		try {
			Exposer exposer=seckillService.exportSeckillUrl(seckillId);
			result=new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
			return new SeckillResult<Exposer>(false, e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/{seckillId}/{md5}/execution",
						method=RequestMethod.POST)
	@ResponseBody
	public SeckillResult<SeckillExcution> excute(@PathVariable("seckillId") Long seckillId,
													@PathVariable("md5") String md5,
													@ CookieValue(value="killPhone",required=false)Long phone){
		if(phone==null) {
			return new SeckillResult<SeckillExcution>(false, "δע��");
		}
			try {
				SeckillExcution excution=seckillService.excuteSeckill(seckillId, phone, md5);
				return new SeckillResult<SeckillExcution>(true, excution);
			} catch (RepeatKillException e) {
				SeckillExcution excution=new SeckillExcution(SeckillStateEnum.REPEAT_KILL, seckillId);
				return new SeckillResult<SeckillExcution>(true, excution);
			} catch (SeckillCloseException e) {
				SeckillExcution excution=new SeckillExcution(SeckillStateEnum.END, seckillId);
				return new SeckillResult<SeckillExcution>(true, excution);
			} catch (Exception e) {
				SeckillExcution excution=new SeckillExcution(SeckillStateEnum.INNER_ERROR, seckillId);
				return new SeckillResult<SeckillExcution>(true, excution);
			}
	}
	
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> time(){
		Date now =new Date();
		return new SeckillResult<Long>(true, now.getTime());
	}
}
