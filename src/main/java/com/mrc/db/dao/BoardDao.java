package com.mrc.db.dao;

import com.mrc.db.dao.impl.IBoardDao;
import com.mrc.db.dto.board.board_cond;
import com.mrc.db.dto.board.t_board;
import com.mrc.db.dto.board.t_board_read;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.mapper.GlobalMapper;
import com.mrc.framework.Global;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BoardDao implements IBoardDao<board_cond, t_board, t_board_read> {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_board> getList(board_cond cond) {
		// TODO Auto-generated method stub
		if (cond.getMember_code() != null) {
			t_board_read condRead = t_board_read.builder().board_type(cond.getBoard_type())
					.insert_code(cond.getMember_code()).build();
			List<t_board_read> list = GlobalMapper.BoardMapper.getReadList(condRead);
			if (list.size() > 0 && !Global.DataInfo.isNullEmpty(list.get(0).getBoard_codes()))
				cond.setBoard_codes(list.get(0).getBoard_codes());
		}
		return GlobalMapper.BoardMapper.getList(cond);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public t_board getData(board_cond cond) {
		// TODO Auto-generated method stub

		List<t_board> list = GlobalMapper.BoardMapper.getList(cond);
		if (list.size() == 1)
			return list.get(0);
		else
			return null;
	}

	@Transactional
	@Override
	public ResultData saveList(List<t_board> list) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			for (int i = 0; i < list.size(); i++) {
				GlobalMapper.BoardMapper.save(list.get(i));
			}
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData save(t_board data) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			
			GlobalMapper.BoardMapper.save(data);
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_board_read> getReadList(t_board_read cond) {
		// TODO Auto-generated method stub
		return GlobalMapper.BoardMapper.getReadList(cond);
	}

	@Transactional
	@Override
	public ResultData read_update(t_board_read data) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.BoardMapper.read_update(data);
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData delete(t_board data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.BoardMapper.delete(data);
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}
}
