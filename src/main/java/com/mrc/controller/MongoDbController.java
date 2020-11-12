package com.mrc.controller;

import com.mrc.db.dao.GlobalDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.mongoDb.*;
import com.mrc.framework.Global;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MongoDbController {
	@ResponseBody
	@PostMapping("/mongodb/insertLog")
	public ResultData insertLog(@RequestBody t_log data) {
		if(Global.LoginInfo.getIsLogin())
		{
			data.setMember_code(Global.LoginInfo.getMember().getMember_code());
			data.setMember_name(Global.LoginInfo.getMember().getMember_name());
		}
		return GlobalDao.MongodbDao.save(data);
	}
	
	@ResponseBody
	@PostMapping("/mongodb/logpagelist")
	public log_list loglist(@RequestBody log_cond cond) {
		if(!Global.LoginInfo.isAdmin())
		{
			cond.setMember_code(Global.LoginInfo.getMember().getMember_code());
		}
		log_list rtn = log_list.builder().build();
		rtn.setList(GlobalDao.MongodbDao.getList(cond));
		rtn.setRowCount(GlobalDao.MongodbDao.getLogCount(cond));
	 	return rtn;
	}
	
	
	@ResponseBody
	@PostMapping("/mongodb/insertLogAdPlace")
	public ResultData insertLogAdPlace(@RequestBody List<log_adPlace> list) {
		
		return GlobalDao.MongodbDao.logAdPlaceSave(list);
	}
	
	@ResponseBody
	@PostMapping("/mongodb/logAdplacelist")
	public List<log_adPlace> logAdplacelist(@RequestBody log_adPlace_cond cond) {
		return  GlobalDao.MongodbDao.getLogAdPlaceList(cond);
	}
	
	/*@ResponseBody
	@PostMapping("/mongodb/adlist")
	public List<T_AD_PLAY_LOG_MONGO> mongodbAdlist(@RequestBody t_log data) {
		List<T_AD_PLAY_LOG_MONGO> list =  GlobalDao.MongodbDao.getAdLog();
		return list;
	}*/
}
