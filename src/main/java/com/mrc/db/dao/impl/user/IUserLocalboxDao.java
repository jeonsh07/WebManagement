package com.mrc.db.dao.impl.user;

import com.mrc.db.dto.user.userLocalboxDetail;

public interface IUserLocalboxDao<userSearchCond, userLocalboxList> extends IUserBaseDao<userSearchCond, userLocalboxList> {
	userLocalboxDetail getDetail(userSearchCond cond);
}
