package com.mrc.db.dao;

import com.mrc.db.dao.impl.IBannerDao;
import com.mrc.db.dto.address.t_address_area;
import com.mrc.db.dto.banner.*;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.dto.keyword.t_keyword;
import com.mrc.db.mapper.GlobalMapper;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kyh
 * @Date: 2020. 6. 10.
 */

@Repository
public class BannerDao implements IBannerDao<banner_cond, t_banner> {
	@Override
	public List<t_banner> getList(banner_cond cond) {
		return null;
	}

	@Override
	public t_banner getData(banner_cond cond) {
		t_banner banner = GlobalMapper.BannerMapper.getData(cond);

		return banner;
	}

	@Transactional
	@Override
	public ResultData save(t_banner data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public ResultData saveList(List<t_banner> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getBannerStoreList(banner_cond cond) {
		List<t_banner> bannerList = GlobalMapper.BannerMapper.getBannerStoreList(cond);
		Integer totalBannerNum = GlobalMapper.BannerMapper.getTotalBannerNum();

		Map<String, Object> mapper = new HashMap<String, Object>();

		mapper.put("bannerList", bannerList);
		mapper.put("totalBannerNum", totalBannerNum);

		return mapper;
	}

	@Transactional
	@Override
	public ResultData save(t_banner banner, List<t_keyword> keywords, List<t_banner_place> places,
			List<t_banner_sub> bannerSub, List<List<t_banner_place_square>> placeSquare) {

		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			Integer bannerCode = banner.getBanner_code();
			Integer insertCode = banner.getInsert_code();

			GlobalMapper.BannerMapper.save(banner);

			if (bannerCode == null) {
				bannerCode = banner.getBanner_code();
			}

			rtn.setData(bannerCode.toString());

			if (places != null) {
				saveBannerPlace(bannerCode, insertCode, places, placeSquare);
			}

			if (keywords != null) {
				saveBannerKeyword(bannerCode, insertCode, saveKeyword(keywords));
			}

			if (bannerSub.size() != 0) {
				saveBannerSub(bannerCode, insertCode, bannerSub);
			}

			if (banner.getStatus() == 1) {
				if (GlobalMapper.BannerMapper.getTempBannerCnt(banner) > TEMP_BANNERE_CNT) {
					Integer tempBannerCode = GlobalMapper.BannerMapper.getDeleteTempBannerCode(banner);

					GlobalMapper.BannerMapper.delete(tempBannerCode);
				}
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional
	@Override
	public List<Integer> saveKeyword(List<t_keyword> keywords) throws Exception {
		List<Integer> keywordCodes = new ArrayList<Integer>();

		for (t_keyword keyword : keywords) {
			keywordCodes.add(Integer.parseInt(GlobalDao.KeywordDao.save(keyword).data));
		}

		return keywordCodes;
	}

	@Transactional
	@Override
	public void saveBannerKeyword(Integer bannerCode, Integer insertCode, List<Integer> keywrodCodes) {

		t_banner_keyword bannerKeyword = new t_banner_keyword();

		bannerKeyword.setBanner_code(bannerCode);
		bannerKeyword.setInsert_code(insertCode);

		GlobalMapper.BannerMapper.deleteBannerKeyword(bannerCode);

		for (Integer keywordCode : keywrodCodes) {
			bannerKeyword.setKeyword_code(keywordCode);

			GlobalMapper.BannerMapper.saveBannerKeyword(bannerKeyword);
		}
	}

	@Transactional
	@Override
	public void saveBannerPlace(Integer bannerCode, Integer insertCode, List<t_banner_place> places,
			List<List<t_banner_place_square>> placeSquare) {

		GlobalMapper.BannerMapper.deleteBannerPlace(bannerCode);

		Integer index = 0;
		for (t_banner_place place : places) {
			place.setBanner_code(bannerCode);
			place.setInsert_code(insertCode);
			GlobalMapper.BannerMapper.saveBannerPlace(place);

			List<t_banner_place_square> tempPlaceSquare = placeSquare.get(index++);

			for (t_banner_place_square square : tempPlaceSquare) {
				t_address_area area = GlobalMapper.BannerMapper.getAddressArea(square.getArea_code());

				square.setBanner_code(bannerCode);
				square.setSquare_type(area.getLevel());
				square.setInsert_code(insertCode);
				square.setPlace_code(place.getPlace_code());

				GlobalMapper.BannerMapper.saveBannerPlaceSquare(square);
			}
		}
	}

	@Transactional
	@Override
	public void saveBannerSub(Integer bannerCode, Integer insertCode, List<t_banner_sub> bannerSub) {
		GlobalMapper.BannerMapper.deleteBannerSub(bannerCode);
		for (t_banner_sub banner : bannerSub) {
			banner.setBanner_code(bannerCode);
			banner.setInsert_code(insertCode);
			GlobalMapper.BannerMapper.saveBannerSub(banner);
		}
	}

	@Override
	public List<List<t_banner_place_tag>> getPlaceTag(List<banner_place_tag_cond> cond) {
		List<List<t_banner_place_tag>> bannerPlaceList = new ArrayList<List<t_banner_place_tag>>();

		for (banner_place_tag_cond tag : cond) {
			tag.set_radius_type(getRadiusType(tag.get_radius_type()));
			bannerPlaceList.add(GlobalMapper.BannerMapper.getPlaceTag(tag));
		}
		return bannerPlaceList;
	}

	@Override
	public Integer getRadiusType(Integer type) {
		return GlobalMapper.BannerMapper.getRadiusType(type);
	}

	@Override
	public List<t_banner> getSimpleTempBanner(banner_cond cond) {
		return GlobalMapper.BannerMapper.getSimpleTempBanner(cond);
	}

	@Override
	public Map<String, Object> getBanner(Integer bannerCode) {

		List<String> bannerKeyword = GlobalMapper.BannerMapper.getBannerKeyword(bannerCode);
		List<t_banner_place> bannerPlace = GlobalMapper.BannerMapper.getBannerPlace(bannerCode);
		List<t_banner_place_square> bannerPlaceSquare = GlobalMapper.BannerMapper.getBannerPlaceSquare(bannerCode);
		List<t_banner_sub> bannerSub = GlobalMapper.BannerMapper.getBannerSub(bannerCode);

		Map<String, Object> info = new HashedMap<>();

		info.put("keywords", bannerKeyword);
		info.put("places", bannerPlace);
		info.put("placeSquare", bannerPlaceSquare);
		info.put("bannerSub", bannerSub);

		return info;
	}

	@Transactional
	@Override
	public ResultData saveBannerStatus(t_banner data) throws Exception {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.BannerMapper.saveBannerStatus(data);
			
			if (data.getStatus() == BANNER_SERVICE_OFF) {
				GlobalMapper.MediaShopMapper.deleteLocalBoxBanner(data.getBanner_code());
			}
			else if (data.getStatus() == BANNER_SERVICE_ON) {
				data.setLocalbox_code(GlobalMapper.MediaShopMapper.getLocalBoxCode(data.getInsert_code()));
				
				if(data.getLocalbox_code() != null) {
					Short seq = (short) (GlobalMapper.MediaShopMapper.getLocalBoxBannerCount(data.getInsert_code()).shortValue() + 1);
				
					data.setSeq(seq);
					GlobalMapper.MediaShopMapper.saveLocalboxBanner(data);
				}
			}
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}
	
	@Transactional
	@Override
	public ResultData delete(banner_cond cond) throws Exception {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();

		try {
			Integer bannerCode = cond.getBanner_code();
			GlobalMapper.BannerMapper.delete(bannerCode);
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}

		return rtn;
	}

	@Override
	public t_banner_share_list getReqBannerShareList(banner_cond cond) {
		t_banner_share_list shares = new t_banner_share_list();
		shares.setShares(GlobalMapper.BannerMapper.getReqBannerShareList(cond));
		shares.setTotal_num(GlobalMapper.BannerMapper.getTotalBannerNum());

		return shares;
	}

	@Override
	public t_banner_share_list getResBannerShareList(banner_cond cond) {
		t_banner_share_list shares = new t_banner_share_list();
		shares.setShares(GlobalMapper.BannerMapper.getResBannerShareList(cond));
		shares.setTotal_num(GlobalMapper.BannerMapper.getTotalBannerNum());

		return shares;
	}
}
