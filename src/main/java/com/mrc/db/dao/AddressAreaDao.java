package com.mrc.db.dao;

import com.mrc.db.dao.impl.IAddressAreaDao;
import com.mrc.db.dto.address.address_cond;
import com.mrc.db.dto.address.location_dong;
import com.mrc.db.dto.address.t_address_area;
import com.mrc.db.dto.common.Kakaomap_cond;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.mapper.GlobalMapper;
import com.mrc.framework.Global;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressAreaDao implements IAddressAreaDao<t_address_area> {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_address_area> getList(t_address_area cond) {
		// TODO Auto-generated method stub
		return GlobalMapper.AddressArea.getList(cond);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public t_address_area getData(t_address_area cond) {
		// TODO Auto-generated method stub
		 List<t_address_area> list = GlobalMapper.AddressArea.getList(cond);
		 if(list.size() == 1) return list.get(0);
		return null;
	}
	
	@Transactional
	@Override
	public ResultData saveList(List<t_address_area> list) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		// TODO Auto-generated method stub
		for(t_address_area data : list) GlobalMapper.AddressArea.save(data);
		return rtn;
	}
	
	@Transactional
	@Override
	public ResultData save(t_address_area data) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		GlobalMapper.AddressArea.save(data);
		return rtn;
	}
	@Transactional
	@Override
	public ResultData update(t_address_area data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		
		try {
			GlobalMapper.AddressArea.update(data);
		}catch(Exception ex)
		{
			data.setCode("");
			rtn.setEnResultType(enResultType.Error);
			rtn.setMessage(ex.getMessage());
		}
		// TODO Auto-generated method stub
		return rtn;
	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<location_dong> getCodeByAddress(address_cond cond) {
		// TODO Auto-generated method stub
		return GlobalMapper.AddressArea.getCodeByAddress(cond);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<location_dong> getAddressAreaBylocation(Kakaomap_cond cond)
	{
		List<location_dong> rtn = new ArrayList<location_dong>();
		ResponseEntity<String> response = Global.KakaoApi.call(URI.create("https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=" + cond.getLongi() + "&y=" + cond.getLat()));
		JSONParser jsonParser = new JSONParser(); 
		
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString());
			JSONArray docuArray = (JSONArray) jsonObject.get("documents");
			
			if(docuArray.size() > 0)
			{
				JSONObject docuObject = (JSONObject) docuArray.get(docuArray.size()-1);
				address_cond Cond2 = address_cond.builder()
									.sido_name(docuObject.get("region_1depth_name").toString())
									.sigungu_name(docuObject.get("region_2depth_name").toString())
									.dong2_name(docuObject.get("region_3depth_name").toString()).build();
				rtn = GlobalMapper.AddressArea.getCodeByAddress(Cond2);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rtn;
	}
}
