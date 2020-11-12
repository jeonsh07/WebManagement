package com.mrc.db.dao.user;

import com.mrc.db.dao.impl.user.IUserBannerDao;
import com.mrc.db.dto.user.userBannerDetail;
import com.mrc.db.dto.user.userBannerList;
import com.mrc.db.dto.user.userSearchCond;
import com.mrc.db.mapper.GlobalMapper;
import com.mrc.db.mapper.user.GlobalUserMapper;
import com.mrc.framework.Global;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserBannerDao implements IUserBannerDao<userSearchCond, userBannerList> {
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<userBannerList> getList(userSearchCond cond) {
		// TODO Auto-generated method stub
		return GlobalUserMapper.UserBanner.getList(cond);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public userBannerDetail getUserBannerDetail(userSearchCond cond) {
		// TODO Auto-generated method stub
		if(Global.LoginInfo.getIsLogin()) cond.setMember_code(Global.LoginInfo.getMember().getMember_code());
		userBannerDetail rtn = GlobalUserMapper.UserBanner.getDetail(cond);
		rtn.setSubList(GlobalMapper.BannerMapper.getBannerSub(cond.getSearch_code()));
		return rtn;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<userBannerList> getUserBannerDetailList(userSearchCond cond) {
		// TODO Auto-generated method stub
		return null;
	}
}
