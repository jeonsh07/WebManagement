package com.mrc.db.dao;

import com.mrc.db.dao.impl.IKeywordDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.dto.keyword.keyword_cond;
import com.mrc.db.dto.keyword.t_keyword;
import com.mrc.db.mapper.GlobalMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: kyh
 * @Date: 2020. 4. 2.
 */

@Repository
public class KeywordDao implements IKeywordDao<keyword_cond, t_keyword> {
	@Override
	public List<t_keyword> getList(keyword_cond cond) {
		// TODO Auto-generated method stub
		return GlobalMapper.KeywordMapper.getList(cond);
	}
	
	@Override
	public List<t_keyword> getAutoList(keyword_cond cond) {
		// TODO Auto-generated method stub
		return GlobalMapper.KeywordMapper.getAutoList(cond);
	}

	@Override
	public t_keyword getData(keyword_cond cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultData saveList(List<t_keyword> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public ResultData save(t_keyword data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.KeywordMapper.save(data);
			rtn.data = data.getKeyword_code().toString();
			
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}
}
