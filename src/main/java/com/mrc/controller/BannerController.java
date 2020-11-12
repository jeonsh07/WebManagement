package com.mrc.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrc.db.dao.GlobalDao;
import com.mrc.db.dto.banner.*;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.keyword.t_keyword;
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
 * @Date: 2020. 5. 17.
 */

@Controller
public class BannerController {
	@ResponseBody
	@PostMapping("/banner/getdata")
	public t_banner getData(@RequestBody banner_cond cond) {
		return GlobalDao.BannerDao.getData(cond);
	}

	@ResponseBody
	@PostMapping("/banner/getbannerstorelist")
	public Map<String, Object> GetBannerStoreList(@RequestBody banner_cond cond) {
		return GlobalDao.BannerDao.getBannerStoreList(cond);
	}

	@ResponseBody
	@PostMapping("/banner/save")
	public ResultData save(@RequestBody Map<String, Object> map) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		t_banner banner = mapper.convertValue(map.get("banner"), t_banner.class);

		byte[] json = mapper.writeValueAsBytes(map.get("keywords"));
		List<t_keyword> keywords = mapper.readValue(json, new TypeReference<List<t_keyword>>() {
		});

		json = mapper.writeValueAsBytes(map.get("places"));
		List<t_banner_place> places = mapper.readValue(json, new TypeReference<List<t_banner_place>>() {
		});

		json = mapper.writeValueAsBytes(map.get("bannerSub"));
		List<t_banner_sub> bannerSub = mapper.readValue(json, new TypeReference<List<t_banner_sub>>() {
		});

		json = mapper.writeValueAsBytes(map.get("placeSquare"));
		List<List<t_banner_place_square>> placeSquare = mapper.readValue(json, new TypeReference<List<List<t_banner_place_square>>>() {
		});

		if (Global.LoginInfo.getIsLogin()) {
			banner.setInsert_code(Global.LoginInfo.getMember().getMember_code());
		} else {
			banner.setInsert_code(1);
		}

		return GlobalDao.BannerDao.save(banner, keywords, places, bannerSub, placeSquare);
	}

	@ResponseBody
	@PostMapping("/banner/getplacetag")
	public List<List<t_banner_place_tag>> getPlaceTag(@RequestBody List<banner_place_tag_cond> cond) {
		return GlobalDao.BannerDao.getPlaceTag(cond);
	}

	@ResponseBody
	@PostMapping("/banner/getsimpletempbanner")
	public List<t_banner> getSimpleTempBanner(@RequestBody banner_cond cond) {
		return GlobalDao.BannerDao.getSimpleTempBanner(cond);
	}

	@ResponseBody
	@PostMapping("/banner/getbanner")
	public Map<String, Object> getBanner(@RequestBody Integer bannerCode) {
		return GlobalDao.BannerDao.getBanner(bannerCode);
	}

	@ResponseBody
	@PostMapping("/banner/savebannerstatus")
	public ResultData saveBannerStatus(@RequestBody t_banner data) throws Exception {
		if (Global.LoginInfo.getIsLogin()) {
			data.setInsert_code(Global.LoginInfo.getMember().getMember_code());
		} else {
			data.setInsert_code(1);
		}

		return GlobalDao.BannerDao.saveBannerStatus(data);
	}

	@ResponseBody
	@PostMapping("/banner/delete")
	public ResultData delete(@RequestBody banner_cond cond) throws Exception {
		return GlobalDao.BannerDao.delete(cond);
	}

	@ResponseBody
	@PostMapping("/bannersharelist/getreqlist")
	public t_banner_share_list getReqBannerShareList(@RequestBody banner_cond cond) {
		return GlobalDao.BannerDao.getReqBannerShareList(cond);
	}

	@ResponseBody
	@PostMapping("/bannersharelist/getreslist")
	public t_banner_share_list getResBannerShareList(@RequestBody banner_cond cond) {
		return GlobalDao.BannerDao.getResBannerShareList(cond);
	}
}
