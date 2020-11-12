package com.mrc.db.mapper;

import com.mrc.db.dto.address.t_address_area;
import com.mrc.db.dto.banner.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author kyh
 *
 * 2020.06.15
 */


@Mapper
public interface IBannerMapper {
	t_banner getData(banner_cond cond);
	
	List<t_banner> getList(banner_cond cond);
	
	List<t_banner> getBannerStoreList(banner_cond cond);
	
	Integer getTotalBannerNum();
	
	void save(t_banner data);
	
	Integer getDeleteTempBannerCode(t_banner data);
	
	void delete(Integer banner_code);
	
	void deleteBannerPlaceSquare(Integer place_code);
	
	void deleteBannerPlace(Integer banner_code);
	
	void saveBannerPlace(t_banner_place data);
	
	t_address_area getAddressArea(String code);
	
	void saveBannerPlaceSquare(t_banner_place_square data);
	
	void deleteBannerKeyword(Integer banner_code);
	
	void saveBannerKeyword(t_banner_keyword data);
	
	void deleteBannerSub(Integer banner_code);
	
	void saveBannerSub(t_banner_sub data);
	
	List<t_banner_place_tag> getPlaceTag(banner_place_tag_cond cond);
	
	Integer getRadiusType(Integer type);
	
	Integer getTempBannerCnt(t_banner data);
	
	List<t_banner> getSimpleTempBanner(banner_cond cond);
	
	List<String> getBannerKeyword(Integer banner_code);
	
	List<t_banner_place> getBannerPlace(Integer banner_code);
	
	List<t_banner_place_square> getBannerPlaceSquare(Integer banner_code);
	
	List<t_banner_sub> getBannerSub(Integer banner_code);
	
	void saveBannerStatus(t_banner data);

	List<t_banner_request> getReqBannerShareList(banner_cond cond);

	List<t_banner_request> getResBannerShareList(banner_cond cond);
}
