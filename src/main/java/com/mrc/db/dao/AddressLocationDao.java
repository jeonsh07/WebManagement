package com.mrc.db.dao;

import com.mrc.db.dao.impl.IAddressLocationDao;
import com.mrc.db.dto.address.address_location_cond;
import com.mrc.db.dto.address.location_dong;
import com.mrc.db.dto.address.t_address_location;
import com.mrc.db.dto.address.t_address_location1;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.mapper.GlobalMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AddressLocationDao implements IAddressLocationDao<address_location_cond, t_address_location> {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_address_location> getList(address_location_cond cond) {
		// TODO Auto-generated method stub
		
		if(cond.getPage_size()  == null) cond.setPage_size(10);
		if(cond.getPage()  != null )
		{
			if(cond.getStartRow() == null) cond.setStartRow((cond.getPage() -1) * cond.getPage_size());
		}
		return GlobalMapper.AddressLocationMapper.getList(cond);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public t_address_location getData(address_location_cond cond) {
		// TODO Auto-generated method stub
		cond.setStartRow(0);
		cond.setPage_size(10);
		List<t_address_location> list = GlobalMapper.AddressLocationMapper.getList(cond);
		if(list.size() == 1) return list.get(0);
		else
			return null;
	}

	@Transactional
	@Override
	public ResultData saveList(List<t_address_location> list) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			for (int i = 0; i < list.size(); i++) {
				GlobalMapper.AddressLocationMapper.save(list.get(i));
			}
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData save(t_address_location data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
				GlobalMapper.AddressLocationMapper.save(data);
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<location_dong> getCurrentDong(address_location_cond cond) {
		// TODO Auto-generated method stub
		List<location_dong> rtn = GlobalMapper.AddressLocationMapper.getCurrentDong(cond);
		return rtn;
	}
	@Transactional
	@Override
	public ResultData addressLocation1Save(t_address_location1 data) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			rtn.data = GlobalMapper.AddressLocation1Mapper.save(data).toString();
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}
}

