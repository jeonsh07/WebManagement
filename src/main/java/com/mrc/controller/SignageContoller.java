package com.mrc.controller;

import com.mrc.db.dao.GlobalDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.signage.signage_cond;
import com.mrc.db.dto.signage.t_signage;
import com.mrc.db.dto.signage.t_signage_place;
import com.mrc.framework.Global;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SignageContoller {
	@ResponseBody
	@PostMapping("/signage/getSignageList")
	public List<t_signage> getSignageList(@RequestBody signage_cond cond) {
		return GlobalDao.SignageDao.getList(cond);
	}
	
	@ResponseBody
	@PostMapping("/signage/getSignage")
	public t_signage getSignage(@RequestBody signage_cond cond) {
		return GlobalDao.SignageDao.getData(cond);
	}
	
	@ResponseBody
	@PostMapping("/signage/signageSave")
	public ResultData signageSave(@RequestBody t_signage data) {
		if(Global.LoginInfo.getIsLogin())
			data.setInsert_code(Global.LoginInfo.getMember().getMember_code());
		return GlobalDao.SignageDao.save(data);
	}

	@ResponseBody
	@PostMapping("/signage/signageDelete")
	public ResultData signageDelete(@RequestBody t_signage data) {

		return GlobalDao.SignageDao.delete(data);
	}
	
	@ResponseBody
	@PostMapping("/signage/signagePlaceSave")
	public ResultData signagePlaceSave(@RequestBody t_signage_place data) {
		if(Global.LoginInfo.getIsLogin())
			data.setInsert_code(Global.LoginInfo.getMember().getMember_code());
		return GlobalDao.SignageDao.signagePlaceSave(data);
	}
	
	@ResponseBody
	@PostMapping("/signage/getSignagePlaceList")
	public List<t_signage_place> getSignagePlaceList(@RequestBody signage_cond cond) {
		return GlobalDao.SignageDao.getSignagePlace(cond);
	}
	
	@ResponseBody
	@PostMapping("/signage/signagePlaceDelete")
	public ResultData signagePlaceDelete(@RequestBody t_signage_place data) {
		return GlobalDao.SignageDao.signagePlaceDelete(data);
	}

}
