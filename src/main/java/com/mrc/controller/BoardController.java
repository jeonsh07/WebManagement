package com.mrc.controller;

import com.mrc.db.dao.GlobalDao;
import com.mrc.db.dto.board.board_cond;
import com.mrc.db.dto.board.t_board;
import com.mrc.db.dto.board.t_board_read;
import com.mrc.db.dto.common.ResultData;
import com.mrc.framework.Global;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {
	@ResponseBody
	@PostMapping("/board/getlist")
	public List<t_board> getBoardList(@RequestBody board_cond cond) {
		try {
			if (Global.LoginInfo.getIsLogin() == true)
			cond.setMember_code(Global.LoginInfo.getMember().getMember_code());
		} catch (Exception ex) {
			System.out.println("getBoardList >> " + ex.getMessage());
			cond.setMember_code(null);
		}
		List<t_board> rtn = GlobalDao.BoardDao.getList(cond);
		return rtn;
	}

	@ResponseBody
	@PostMapping("/board/save")
	public ResultData boardSave(@RequestBody t_board data) {
		if (Global.LoginInfo.getIsLogin() == true)
			data.setInsert_code(Global.LoginInfo.getMember().getMember_code());
		else
			data.setInsert_code(1);
		return GlobalDao.BoardDao.save(data);
	}

	
	

	@ResponseBody
	@PostMapping("/board/read")
	public ResultData boardread(@RequestBody t_board_read data) {

		if (Global.LoginInfo.getIsLogin() == true)
			data.setInsert_code(Global.LoginInfo.getMember().getMember_code());
		else
			data.setInsert_code(1);
		return GlobalDao.BoardDao.read_update(data);
	}

	@ResponseBody
	@PostMapping("/board/delete")
	public ResultData boardDelete(@RequestBody t_board data) {

		return GlobalDao.BoardDao.delete(data);
	}
}
