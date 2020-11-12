/**
 * 
 */
package com.mrc.db.dao;

import com.mrc.db.dao.impl.ICommonDao;
import com.mrc.db.dto.address.t_address_area;
import com.mrc.db.dto.common.*;
import com.mrc.db.mapper.GlobalMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 공통 Dao
 * 
 * @author jsh
 *
 */
@Repository
public class CommonDao implements ICommonDao<common_cond, t_common, SelectItem> {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_common> getList(common_cond cond) {

		List<t_common> list = GlobalMapper.CommonMapper.getList(cond);
		// return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		return list;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public t_common getData(common_cond cond) {
		// TODO Auto-generated method stub
		List<t_common> list = GlobalMapper.CommonMapper.getList(cond);
		if (list.size() == 1)
			return list.get(0);
		else
			return null;
	}

	@Transactional
	@Override
	public ResultData saveList(List<t_common> list) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			for (int i = 0; i < list.size(); i++) {
				GlobalMapper.CommonMapper.save(list.get(i));
			}
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData save(t_common data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.CommonMapper.save(data);
			rtn = ResultData.builder().enResultType(enResultType.Info).data(data.getCommon_code().toString()).build();
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<SelectItem> getSelectItemList(common_cond cond) {
		// TODO Auto-generated method stub
		List<SelectItem> rtn = GlobalMapper.CommonMapper.getSelectItemList(cond);
		return rtn;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_address_area> getAddressAreaList(t_address_area cond) {
		// TODO Auto-generated method stub
		List<t_address_area> rtn = GlobalMapper.CommonMapper.getAddressAreaList(cond);
		return rtn;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<String> getTableToHtml() {
		// TODO Auto-generated method stub
		return GlobalMapper.CommonMapper.getTableToHtml();
	}

	@Transactional
	@Override
	public ResultData delete(t_common data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.CommonMapper.delete(data);
			rtn = ResultData.builder().enResultType(enResultType.Info).data(data.getCommon_code().toString()).build();
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}
}
