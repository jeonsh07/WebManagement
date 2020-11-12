package com.mrc.db.dao.user;

import com.mrc.db.dao.impl.user.IUserCommonDao;
import com.mrc.db.dto.user.userAlamMsgCond;
import com.mrc.db.dto.user.userAlamMsgData;
import com.mrc.db.mapper.user.GlobalUserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCommonDao implements IUserCommonDao<userAlamMsgCond, userAlamMsgData> {

	@Override
	public List<userAlamMsgData> getList(userAlamMsgCond cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<userAlamMsgData> getAlamMsgList(userAlamMsgCond cond) {
		// TODO Auto-generated method stub
		return GlobalUserMapper.UserCommonMapper.getAlamMsgList(cond);
	}
}
