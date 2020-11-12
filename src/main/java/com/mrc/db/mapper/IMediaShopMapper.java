package com.mrc.db.mapper;

import com.mrc.db.dto.banner.t_banner;
import com.mrc.db.dto.mediaShop.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author kyh
 *
 * 2020.07.29
 */

@Mapper
public interface IMediaShopMapper {
	t_localbox getLocalBox(localbox_cond cond);
	List<String> getLocalBoxKeyword(localbox_cond cond);
	t_localbox_place getLocalBoxPlace(localbox_cond cond);
	void save(t_localbox data);
	void deleteLocalboxKeyword(Integer localbox_code);
	void saveLocalboxKeyword(t_localbox_keyword data);
	void deleteLocalboxPlace(t_localbox_place data);
	void saveLocalboxPlace(t_localbox_place data);
	t_localbox_simple getLocalBoxSimple(localbox_cond cond);
	List<t_localbox_banner> getLocalBoxBannerList(localbox_cond cond);
	List<t_localbox_banner> getLocalBoxPreviewBannerList(localbox_cond cond);
	List<t_localbox_place> getLocalBoxBannerPlaceList(localbox_cond cond);
	Integer getTotalNum();
	Integer getLocalBoxBannerCount(Integer member_code);
	Integer getLocalBoxCode(Integer memeber_code);
	void deleteLocalBoxBanner(Integer banner_code);
	void saveLocalboxBanner(t_banner data);
	void updateLocalBoxBanner(t_localbox_banner banners);
	Integer getLocalBoxCode(localbox_cond cond);
	List<t_localer> getLocalerList(localbox_cond cond);
	void deleteBannerShare(t_localbox_banner_request data);
	void saveBannerShare(t_localbox_banner_request data);
	List<t_localbox_banner_request> getBannerShare(localbox_cond cond);
}
