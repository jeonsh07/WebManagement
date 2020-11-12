package com.mrc.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrc.db.dao.GlobalDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.keyword.t_keyword;
import com.mrc.db.dto.mediaShop.*;
import com.mrc.framework.Global;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author kyh
 * @Date: 2020. 7. 29.
 */

@Controller
public class MediaShopController {
	@ResponseBody
	@PostMapping("/mediashop/localbox/getlocalbox")
	public Map<String, Object> getLocalBox(@RequestBody localbox_cond cond) {
		return GlobalDao.MediaShopDao.getLocalBox(cond);
	}
	
	@ResponseBody
	@PostMapping("/mediashop/localbox/save")
	public ResultData save(@RequestBody Map<String, Object> map) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		t_localbox localbox = mapper.convertValue(map.get("localbox"), t_localbox.class);

		byte[] json = mapper.writeValueAsBytes(map.get("keywords"));
		List<t_keyword> keywords = mapper.readValue(json, new TypeReference<List<t_keyword>>() {
		});

		json = mapper.writeValueAsBytes(map.get("place"));
		t_localbox_place place = mapper.readValue(json, new TypeReference<t_localbox_place>() {
		});

		if(Global.LoginInfo.getIsLogin() == true) {
			localbox.setInsert_code(Global.LoginInfo.getMember().getMember_code());
		}
		else {
			localbox.setInsert_code(1);
		}
		
		return GlobalDao.MediaShopDao.save(localbox, keywords, place);
	}
	
	@ResponseBody
	@PostMapping("/mediashop/localbox/getlocalboxsimple")
	public t_localbox_simple getLocalBoxSimple(@RequestBody localbox_cond cond) {
		return GlobalDao.MediaShopDao.getLocalBoxSimple(cond);
	}
	
	@ResponseBody
	@PostMapping("/mediashop/getlocalboxbannerlist")
	public Map<String, Object> getLocalBoxBannerList(@RequestBody localbox_cond cond) {
		if(Global.LoginInfo.getIsLogin() == true) {
			cond.setMember_code(Global.LoginInfo.getMember().getMember_code());
		}
		else {
			cond.setMember_code(1);
		}
		
		return GlobalDao.MediaShopDao.getLocalBoxBannerList(cond);
	}
	
	@ResponseBody
	@PostMapping("/mediashop/getlocalboxpreviewbannerlist")
	public Map<String, Object> getLocalBoxPreviewBannerList(@RequestBody localbox_cond cond) {
		if(Global.LoginInfo.getIsLogin() == true) {
			cond.setMember_code(Global.LoginInfo.getMember().getMember_code());
		}
		else {
			cond.setMember_code(1);
		}
		
		return GlobalDao.MediaShopDao.getLocalBoxPreviewBannerList(cond);
	}
	
	@ResponseBody
	@PostMapping("/mediashop/uploadlocalboxbanner")
	public ResultData uploadlocalboxbanner(@RequestBody List<t_localbox_banner> banners) throws Exception {
		
		return GlobalDao.MediaShopDao.updateLocalBoxBanner(banners);
	}
	
	@ResponseBody
	@PostMapping("/mediashop/localbox/getlocalboxcode")
	public Integer getLocalBoxCode(@RequestBody localbox_cond cond) {
		return GlobalDao.MediaShopDao.getLocalBoxCode(cond);
	}
	
	@ResponseBody
	@PostMapping("/mediashop/getlocalerlist")
	public Map<String, Object> getLocalerList(@RequestBody localbox_cond cond) {
		return GlobalDao.MediaShopDao.getLocalerList(cond);
	}
	
	@ResponseBody
	@PostMapping("/mediashop/savebannershare")
	public ResultData saveBannerShare(@RequestBody List<t_localbox_banner_request> data) throws Exception {
		return GlobalDao.MediaShopDao.saveBannerShare(data);
	}
	
	@ResponseBody
	@PostMapping("/mediashop/getbannershare")
	public List<t_localbox_banner_request> getBannerShare(@RequestBody localbox_cond cond) {
		return GlobalDao.MediaShopDao.getBannerShare(cond);
	}
}
