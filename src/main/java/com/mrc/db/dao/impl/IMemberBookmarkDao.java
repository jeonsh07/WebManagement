package com.mrc.db.dao.impl;

import com.mrc.db.dto.common.ResultData;

public interface IMemberBookmarkDao<t_member_bookmark> extends IBaseDao<t_member_bookmark, t_member_bookmark> {
	ResultData delete(t_member_bookmark data);
}
