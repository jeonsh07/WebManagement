package com.mrc.db.dao;

import com.mrc.db.dao.impl.IMediaShopDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.dto.keyword.t_keyword;
import com.mrc.db.dto.mediaShop.*;
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
 * @author kyh
 *
 * 2020.07.29
 */


@Repository
public class MediaShopDao implements IMediaShopDao<localbox_cond, t_localbox> {
	public Map<String, Object> getLocalBox(localbox_cond cond) {
		t_localbox localBox = GlobalMapper.MediaShopMapper.getLocalBox(cond);
		
		cond.setLocalbox_code(localBox.getLocalbox_code());
		
		List<String> localBoxKeyword = GlobalMapper.MediaShopMapper.getLocalBoxKeyword(cond);
		t_localbox_place localBoxPlace = GlobalMapper.MediaShopMapper.getLocalBoxPlace(cond);
		
		Map<String, Object> info = new HashedMap<>();

		info.put("localBox", localBox);
		info.put("keywords", localBoxKeyword);
		info.put("place", localBoxPlace);

		return info;
	}
	
	@Transactional
	@Override
	public ResultData save(t_localbox localbox, List<t_keyword> keywords, t_localbox_place place) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			Integer localboxCode = localbox.getLocalbox_code();
			Integer insertCode = localbox.getInsert_code();

			GlobalMapper.MediaShopMapper.save(localbox);

			if (localboxCode == null) {
				localboxCode = localbox.getLocalbox_code();
			}

			rtn.setData(localboxCode.toString());

			saveLocalboxPlace(localboxCode, insertCode, place);
			
			if (keywords != null) {
				saveLocalboxKeyword(localboxCode, insertCode, saveKeyword(keywords));
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
	public void saveLocalboxKeyword(Integer localboxCode, Integer insertCode, List<Integer> keywrodCodes) throws Exception {

		t_localbox_keyword localboxKeyword = new t_localbox_keyword();

		localboxKeyword.setLocalbox_code(localboxCode);
		localboxKeyword.setInsert_code(insertCode);

		GlobalMapper.MediaShopMapper.deleteLocalboxKeyword(localboxCode);

		for (Integer keywordCode : keywrodCodes) {
			localboxKeyword.setKeyword_code(keywordCode);

			GlobalMapper.MediaShopMapper.saveLocalboxKeyword(localboxKeyword);
		}
	}

	@Transactional
	@Override
	public void saveLocalboxPlace(Integer localboxCode, Integer insertCode, t_localbox_place place) throws Exception{
		place.setLocalbox_code(localboxCode);
		place.setInsert_code(insertCode);
		
		GlobalMapper.MediaShopMapper.deleteLocalboxPlace(place);
		GlobalMapper.MediaShopMapper.saveLocalboxPlace(place);
	}

	@Override
	public t_localbox_simple getLocalBoxSimple(localbox_cond cond) {
		return GlobalMapper.MediaShopMapper.getLocalBoxSimple(cond);
	}
	
	@Transactional
	@Override
	public Map<String, Object> getLocalBoxBannerList(localbox_cond cond) {
		List<t_localbox_banner> bannerList = GlobalMapper.MediaShopMapper.getLocalBoxBannerList(cond);
		Integer totalNum = GlobalMapper.MediaShopMapper.getTotalNum();
		
		Map<String, Object> mapper = new HashMap<String, Object>();

		mapper.put("bannerList", bannerList);
		mapper.put("totalNum", totalNum);
		
		return mapper;
	}

	@Transactional
	@Override
	public Map<String, Object> getLocalBoxPreviewBannerList(localbox_cond cond) {
		List<t_localbox_banner> bannerList = GlobalMapper.MediaShopMapper.getLocalBoxPreviewBannerList(cond);
		Integer totalNum = GlobalMapper.MediaShopMapper.getTotalNum();
		
		List<Integer> bannerCodes = new ArrayList<Integer>();
		
		for(t_localbox_banner banner : bannerList) {
			bannerCodes.add(banner.getBanner_code());
		}
		
		cond.setBanner_codes(bannerCodes);
		
		List<t_localbox_place> places = GlobalMapper.MediaShopMapper.getLocalBoxBannerPlaceList(cond);
		
		Map<String, Object> mapper = new HashMap<String, Object>();

		mapper.put("bannerList", bannerList);
		mapper.put("totalNum", totalNum);
		mapper.put("places", places);
		
		return mapper;
	}
	
	@Override
	public ResultData updateLocalBoxBanner(List<t_localbox_banner> banners) throws Exception {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		
		try {
			for(t_localbox_banner banner : banners) {
				GlobalMapper.MediaShopMapper.updateLocalBoxBanner(banner);
			}
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		
		return rtn;
	}
	
	@Override
	public Integer getLocalBoxCode(localbox_cond cond) {
		return GlobalMapper.MediaShopMapper.getLocalBoxCode(cond);
	}

	@Override
	public Map<String, Object> getLocalerList(localbox_cond cond) {
		List<t_localer> localers = GlobalMapper.MediaShopMapper.getLocalerList(cond);
		Integer totalNum = GlobalMapper.MediaShopMapper.getTotalNum();
		
		Map<String, Object> mapper = new HashMap<String, Object>();
		
		mapper.put("localers", localers);
		mapper.put("totalNum", totalNum);
		
		return mapper;
	}
	
	@Transactional
	@Override
	public ResultData saveBannerShare(List<t_localbox_banner_request> data) throws Exception {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		
		try 
		{
			for (t_localbox_banner_request reqBanner : data) {
				GlobalMapper.MediaShopMapper.deleteBannerShare(reqBanner);
				
				if(reqBanner.getIs_share()) {
					List<Integer> localers = reqBanner.getLocalers();
					for(Integer approvalMemeberCode : localers) {
						reqBanner.setApproval_member_code(approvalMemeberCode);
						GlobalMapper.MediaShopMapper.saveBannerShare(reqBanner);
					}
				} 
				else { 
					GlobalMapper.MediaShopMapper.saveBannerShare(reqBanner);
				}
			}
		} 
		catch (Exception ex) 
		{
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		
		return rtn;
	}
	
	@Override
	public List<t_localbox_banner_request> getBannerShare(localbox_cond cond) {
		return GlobalMapper.MediaShopMapper.getBannerShare(cond);
	}

		
	@Override
	public List<t_localbox> getList(localbox_cond cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public t_localbox getData(localbox_cond cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultData saveList(List<t_localbox> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultData save(t_localbox data) {
		// TODO Auto-generated method stub
		return null;
	}
}
