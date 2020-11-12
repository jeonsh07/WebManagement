package com.mrc.db.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mrc.db.dao.impl.user.IUserBannerDao;
import com.mrc.db.dao.impl.user.IUserCommonDao;
import com.mrc.db.dao.impl.user.IUserLocalboxDao;
import com.mrc.db.dto.user.userAlamMsgCond;
import com.mrc.db.dto.user.userAlamMsgData;
import com.mrc.db.dto.user.userBannerList;
import com.mrc.db.dto.user.userLocalboxList;
import com.mrc.db.dto.user.userSearchCond;

@Component
public class GlobalUserDao {
	public static IUserBannerDao<userSearchCond, userBannerList> UserBannerDao;
	public static IUserLocalboxDao<userSearchCond, userLocalboxList> UserLocalboxDao;
	public static IUserCommonDao<userAlamMsgCond, userAlamMsgData> UserCommonDao;
	@Autowired
	private GlobalUserDao(IUserBannerDao<userSearchCond, userBannerList> userbanner
			             ,IUserLocalboxDao<userSearchCond, userLocalboxList> userLocalboxDao
			             ,IUserCommonDao<userAlamMsgCond, userAlamMsgData> userCommonDao
			             ) {
		UserBannerDao = userbanner;
		UserLocalboxDao = userLocalboxDao;
		UserCommonDao = userCommonDao;
	}
}
