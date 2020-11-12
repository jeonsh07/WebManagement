package com.mrc.db.dao;

import com.mrc.db.dao.impl.IFileDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.dto.file.file_cond;
import com.mrc.db.dto.file.t_file;
import com.mrc.db.mapper.GlobalMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class FileDao implements IFileDao<file_cond,t_file> {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_file> getList(file_cond cond) {
		// TODO Auto-generated method stub
		return GlobalMapper.FileMapper.getList(cond);
		
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public t_file getData(file_cond cond) {
		// TODO Auto-generated method stub
	  List<t_file> list = GlobalMapper.FileMapper.getList(cond);
	  if(list.size() == 1) return list.get(0);
	  else return null;
	}

	@Transactional
	@Override
	public ResultData saveList(List<t_file> list) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			for (int i = 0; i < list.size(); i++) {
				GlobalMapper.FileMapper.save(list.get(i));
			}
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData save(t_file data) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.FileMapper.save(data);
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

}
