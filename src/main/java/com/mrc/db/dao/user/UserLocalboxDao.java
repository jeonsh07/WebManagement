package com.mrc.db.dao.user;

import com.mrc.db.dao.impl.user.IUserLocalboxDao;
import com.mrc.db.dto.user.userLocalboxDetail;
import com.mrc.db.dto.user.userLocalboxList;
import com.mrc.db.dto.user.userSearchCond;
import com.mrc.db.mapper.user.GlobalUserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserLocalboxDao implements IUserLocalboxDao<userSearchCond, userLocalboxList> {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<userLocalboxList> getList(userSearchCond cond) {
		// TODO Auto-generated method stub
		List<userLocalboxList> rtn = GlobalUserMapper.UserLocalboxMapper.getList(cond);
		return rtn;
	}

	@Override
	public userLocalboxDetail getDetail(userSearchCond cond) {
		// TODO Auto-generated method stub
		userLocalboxDetail rtn = GlobalUserMapper.UserLocalboxMapper.getDetail(cond);
		return rtn;
	}
}
