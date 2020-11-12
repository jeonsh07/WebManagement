package com.mrc.db.dao;

import com.mrc.db.dao.impl.IPersistentLoginsDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.member.persistent_logins;
import com.mrc.db.mapper.GlobalMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PersistentLoginsDao  implements IPersistentLoginsDao<persistent_logins> {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<persistent_logins> getList(persistent_logins cond) {
		// TODO Auto-generated method stub
		return GlobalMapper.PersistentLoginsMapper.getTokenList(cond);
	}

	@Transactional
	@Override
	public persistent_logins getData(persistent_logins cond) {
		// TODO Auto-generated method stub
		
		List<persistent_logins> list = GlobalMapper.PersistentLoginsMapper.getTokenList(cond);
		if(list.size()==0 ) return null;
		return list.get(0);
	}

	@Transactional
	@Override
	public ResultData saveList(List<persistent_logins> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public ResultData save(persistent_logins data) {
		// TODO Auto-generated method stub
		persistent_logins cond = persistent_logins.builder().series(data.getSeries()).build();
		List<persistent_logins> rtn = GlobalMapper.PersistentLoginsMapper.getTokenList(cond);
		if(rtn.size()==0)
		{
			GlobalMapper.PersistentLoginsMapper.createToken(data);
		}
		else if(data.getSaveType().equals("D"))
		{
			GlobalMapper.PersistentLoginsMapper.deleteToken(data);
		}
			
		else {
			GlobalMapper.PersistentLoginsMapper.updateToken(data);
		}
		return null;
	}
}
