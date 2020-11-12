package com.mrc.db.dao.impl.user;

import java.util.List;

import com.mrc.db.dto.user.userBannerDetail;

public interface IUserBannerDao<userSearchCond, userBannerList> extends IUserBaseDao<userSearchCond, userBannerList> {
	userBannerDetail getUserBannerDetail(userSearchCond cond);
	List<userBannerList> getUserBannerDetailList(userSearchCond cond);
}
