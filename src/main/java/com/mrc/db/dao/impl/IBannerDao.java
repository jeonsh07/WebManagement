package com.mrc.db.dao.impl;

import com.mrc.db.dto.banner.*;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.keyword.t_keyword;

import java.util.List;
import java.util.Map;

/**
 * @Author: kyh
 * @Date: 2020. 6. 10.
 */

@SuppressWarnings("hiding")
public interface IBannerDao<banner_cond, t_banner> extends IBaseDao<banner_cond, t_banner> {
	public static final Short TEMP_BANNERE_CNT = 10;
	public static final Short BANNER_SERVICE_OFF = 9;
	public static final Short BANNER_SERVICE_ON = 99;
	
	public Map<String, Object> getBannerStoreList(banner_cond cond);
	
	public ResultData save(t_banner banner, List<t_keyword> keywords, List<t_banner_place> places,
			List<t_banner_sub> bannerSub, List<List<t_banner_place_square>> placeSquare);

	public void saveBannerPlace(Integer bannerCode, Integer insertCode, List<t_banner_place> places,
			List<List<t_banner_place_square>> placeSquare) throws Exception;

	public List<Integer> saveKeyword(List<t_keyword> keywords) throws Exception;

	public void saveBannerKeyword(Integer bannerCode, Integer insertCode, List<Integer> keywrodCodes) throws Exception;

	public void saveBannerSub(Integer bannerCode, Integer insertCode, List<t_banner_sub> bannerSub) throws Exception;

	public List<List<t_banner_place_tag>> getPlaceTag(List<banner_place_tag_cond> cond);

	public Integer getRadiusType(Integer type);

	public List<t_banner> getSimpleTempBanner(banner_cond cond);

	public Map<String, Object> getBanner(Integer bannerCode);

	public ResultData saveBannerStatus(t_banner data) throws Exception;
	
	public ResultData delete(banner_cond cond) throws Exception;

	public t_banner_share_list getReqBannerShareList(banner_cond cond);

	public t_banner_share_list getResBannerShareList(banner_cond cond);
}
