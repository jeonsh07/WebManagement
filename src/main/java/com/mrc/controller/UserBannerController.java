package com.mrc.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrc.db.dao.GlobalDao;
import com.mrc.db.dao.user.GlobalUserDao;
import com.mrc.db.dto.address.location_dong;
import com.mrc.db.dto.mongoDb.log_keyword;
import com.mrc.db.dto.user.userBannerDetail;
import com.mrc.db.dto.user.userBannerList;
import com.mrc.db.dto.user.userLocalboxList;
import com.mrc.db.dto.user.userSearchCond;
import com.mrc.framework.Global;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserBannerController {
	@ResponseBody
	@PostMapping("/user/getuserbannerList")
	public List<userBannerList> getList(@RequestBody userSearchCond cond) {
		
		if (Global.LoginInfo.getIsLogin() == true)
			cond.setMember_code(Global.LoginInfo.getMember().getMember_code());
		if(cond.getOrderBy() == null )
			cond.setOrderBy("b1.max_gate DESC, a.search_cnt DESC");
		if(cond.getOrderBy().equals("bd"))
		{
			cond.setOrderBy("e.bookmark_code desc");
		}
		log_keyword logKeyword =  log_keyword.builder()
				.keyword_code(cond.getKeyword_code())
				.keyword_name(cond.getKeyword_name())
				.latitude(cond.getLatitude())
				.longitude(cond.getLongitude())
				.build();
		
		
		if(!Global.DataInfo.isNullEmpty( cond.getCurrentArea()))
		{
			 
			location_dong dong = new Gson().fromJson(cond.getCurrentArea(), new TypeToken<location_dong>(){}.getType());
	
			if(dong != null) {
				//logKeyword = list.get(0);
				/*t_address_location1 loc1 = t_address_location1.builder().code(dong.getCode())
																		.sido_name(dong.getSido_name())
																		.sigungu_name(dong.getSigungu_name())
																		.dong2_name(dong.getDong2_name())
																		.search_latitude(Math.floor( dong.getLatitude() * 100) / 100)
																		.search_longitude(Math.floor( dong.getLongitude() * 100) / 100)
																		.latitude(dong.getLatitude())
																		.longitude(dong.getLongitude())
																		.gage_rate(0d)
																		.search_cnt(BigInteger.valueOf(0))
																		.build();
				ResultData rtnSave =  GlobalDao.AddressLocationDao.addressLocation1Save(loc1);*/
				
				//dong.setIdx(Integer.parseInt(rtnSave.getData()));
				logKeyword.setArea_code(dong.getCode());
				logKeyword.setArea_name(dong.getSido_name() + " " + dong.getSigungu_name() + " " +  dong.getDong2_name());
			    logKeyword.setSquare_code(dong.getSquare_code());
			    if(cond.getLatitude()== null)
			    {
			    	logKeyword.setLatitude(dong.getLatitude());
					logKeyword.setLongitude(dong.getLongitude());	
			    }
				
				
			}
		}
	
		if(Global.LoginInfo.getIsLogin())
		{
			logKeyword.setMember_code(Global.LoginInfo.getMember().getMember_code());
			logKeyword.setMember_name(Global.LoginInfo.getMember().getMember_name());
		}
		else {
			logKeyword.setMember_code(-1);
		}
		GlobalDao.MongodbDao.logKeywordSave(logKeyword);
		List<userBannerList> rtn = GlobalUserDao.UserBannerDao.getList(cond);
		return rtn;
	}
	@ResponseBody
	@PostMapping("/user/getUserBannerDetail")
	 public userBannerDetail getUserBannerDetail(@RequestBody userSearchCond cond)
	 {
		 return GlobalUserDao.UserBannerDao.getUserBannerDetail(cond);
	 }
	
	@ResponseBody
	@PostMapping("/user/getuserlocalboxList")
	public List<userLocalboxList> getLocalboxList(@RequestBody userSearchCond cond) {
		if(cond.getOrderBy() == null) cond.setOrderBy("distance");

		if (Global.LoginInfo.getIsLogin() == true)
			cond.setMember_code(Global.LoginInfo.getMember().getMember_code());
		return GlobalUserDao.UserLocalboxDao.getList(cond);
	}
}

