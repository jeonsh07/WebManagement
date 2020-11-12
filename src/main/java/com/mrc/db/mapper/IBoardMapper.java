package com.mrc.db.mapper;

import com.mrc.db.dto.board.board_cond;
import com.mrc.db.dto.board.t_board;
import com.mrc.db.dto.board.t_board_read;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IBoardMapper {
	List<t_board> getList(board_cond cond);
	void save(t_board data);
	void delete(t_board data);
	void update(t_board data);
	List<t_board_read> getReadList(t_board_read cond);
	void read_update(t_board_read data);
}
