package com.mrc.db.dao;

import com.mrc.db.dao.impl.ISignageDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.dto.signage.signage_cond;
import com.mrc.db.dto.signage.t_signage;
import com.mrc.db.dto.signage.t_signage_place;
import com.mrc.db.mapper.GlobalMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 사이니지 DAO
 */
@Repository
public class SignageDao implements ISignageDao<signage_cond, t_signage> {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_signage> getList(signage_cond cond) {
		// TODO Auto-generated method stub
		return GlobalMapper.SignageMapper.getList(cond);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public t_signage getData(signage_cond cond) {
		// TODO Auto-generated method stub
		List<t_signage> list = GlobalMapper.SignageMapper.getList(cond);
		if(list.size() == 1) return list.get(0);

		return null;
	}

	@Transactional
	@Override
	public ResultData saveList(List<t_signage> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public ResultData save(t_signage data) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.SignageMapper.save(data);
			rtn.data = data.getSign_code().toString();
			if(data.getPlaceList() != null)
			{
				GlobalMapper.SignagePlaceMapper.delete(t_signage_place.builder().sign_code(data.getSign_code()).build());
				for(t_signage_place place : data.getPlaceList())
				{
					place.setInsert_code(data.getInsert_code());
					place.setSign_code(data.getSign_code());
					GlobalMapper.SignagePlaceMapper.save(place);
				}
			}
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData delete(t_signage data) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.SignagePlaceMapper.delete(t_signage_place.builder().sign_code(data.getSign_code()).build());
			GlobalMapper.SignageMapper.delete(data);
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData update(t_signage data) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.SignageMapper.update(data);

		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_signage_place> getSignagePlace(signage_cond cond) {
		// TODO Auto-generated method stub
		return GlobalMapper.SignagePlaceMapper.getList(cond);
	}

	@Transactional
	@Override
	public ResultData signagePlaceSave(t_signage_place data) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			if(data.getPlace_type() == null) data.setPlace_type(1);
			GlobalMapper.SignagePlaceMapper.save(data);
			rtn.data = data.getPlace_code().toString();

		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData signagePlaceDelete(t_signage_place data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.SignagePlaceMapper.delete(data);
			rtn.data = data.getPlace_code().toString();

		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}
}
