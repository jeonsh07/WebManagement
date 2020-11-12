package com.mrc.db.dao.impl;

import com.mrc.db.dto.common.ResultData;

public interface IMenuDao<menu_cond,t_menu> extends IBaseDao<menu_cond,t_menu> {
	public ResultData delete(t_menu data);
}
