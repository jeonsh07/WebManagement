package com.mrc.db.dao.impl.user;

import java.util.List;

public interface IUserCommonDao<userAlamMsgCond, userAlamMsgData> extends IUserBaseDao<userAlamMsgCond, userAlamMsgData> {
	List<userAlamMsgData> getAlamMsgList(userAlamMsgCond cond);
}
