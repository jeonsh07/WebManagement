package com.mrc.db.dao.impl;

import java.util.List;

import com.mrc.db.dto.common.ResultData;

public interface IBoardDao<board_cond, t_board, t_board_read> extends IBaseDao<board_cond, t_board>{
	public List<t_board_read> getReadList(t_board_read cond);
	public ResultData read_update(t_board_read data);
	
	public ResultData delete(t_board data);
	
}
