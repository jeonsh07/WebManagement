package com.mrc.db.dao;

import com.mrc.db.dao.impl.IMapSquareDao;
import com.mrc.db.dto.address.map_square_cond;
import com.mrc.db.dto.address.t_map_square;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.mapper.GlobalMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MapSquareDao implements IMapSquareDao<map_square_cond, t_map_square> {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_map_square> getList(map_square_cond cond) {
		// TODO Auto-generated method stub
		return GlobalMapper.MapSquareMapper.getList(cond);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public t_map_square getData(map_square_cond cond) {
		// TODO Auto-generated method stub
		List<t_map_square> list = GlobalMapper.MapSquareMapper.getList(cond);
		if(list.size() == 1) return list.get(0);
		else return null;
	}

	@Transactional
	@Override
	public ResultData saveList(List<t_map_square> list) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			for (int i = 0; i < list.size(); i++) {
				GlobalMapper.MapSquareMapper.save(list.get(i));
			}
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData save(t_map_square data) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.MapSquareMapper.save(data);
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}
}
