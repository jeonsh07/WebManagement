package com.mrc.db.dao.impl;

import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.keyword.t_keyword;
import com.mrc.db.dto.mediaShop.t_localbox_banner;
import com.mrc.db.dto.mediaShop.t_localbox_banner_request;
import com.mrc.db.dto.mediaShop.t_localbox_place;
import com.mrc.db.dto.mediaShop.t_localbox_simple;

import java.util.List;
import java.util.Map;

/**
 * @author kyh
 *
 * 2020.07.29
 */

@SuppressWarnings("hiding")
public interface IMediaShopDao<localbox_cond, t_localbox>  extends IBaseDao<localbox_cond, t_localbox> {
	 Map<String, Object> getLocalBox(localbox_cond cond);
	
	 ResultData save(t_localbox localbox, List<t_keyword> keywords, t_localbox_place place);

	 List<Integer> saveKeyword(List<t_keyword> keywords) throws Exception;

	 void saveLocalboxKeyword(Integer localboxCode, Integer insertCode, List<Integer> keywrodCodes) throws Exception;

	 void saveLocalboxPlace(Integer bannerCode, Integer insertCode, t_localbox_place places) throws Exception;
	
	 t_localbox_simple getLocalBoxSimple(localbox_cond cond);
	
	 Map<String, Object> getLocalBoxBannerList(localbox_cond cond);
	
	 Map<String, Object> getLocalBoxPreviewBannerList(localbox_cond cond);

	 ResultData updateLocalBoxBanner(List<t_localbox_banner> banners) throws Exception;
	
	 Integer getLocalBoxCode(localbox_cond cond);
	
	 Map<String, Object> getLocalerList(localbox_cond cond);
	
	 ResultData saveBannerShare(List<t_localbox_banner_request> data) throws Exception;

	 List<t_localbox_banner_request> getBannerShare(localbox_cond cond);
}
