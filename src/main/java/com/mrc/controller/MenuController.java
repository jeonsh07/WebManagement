package com.mrc.controller;

import com.mrc.db.dao.GlobalDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.menu.menu_cond;
import com.mrc.db.dto.menu.t_menu;
import com.mrc.framework.Global;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {
	@ResponseBody
	@PostMapping("/menu/getList")
	public List<t_menu> getList(@RequestBody menu_cond cond) {
		return GlobalDao.MenuDao.getList(cond);
	}
	
	@ResponseBody
	@PostMapping("/menu/save")
	public ResultData menuSave(@RequestBody t_menu data)
	{
		
		data.setInsert_code(Global.LoginInfo.getIsLogin() == true ? Global.LoginInfo.getMember().getMember_code() : 1);
		data.setMenu_code(data.getMenu_code() == null ? -1 : data.getMenu_code());
		data.setFull_menu_name(Global.DataInfo.isNullEmpty(data.getFull_menu_name()) ? data.getMenu_name() : data.getFull_menu_name()); 
		
		ResultData rtn = GlobalDao.MenuDao.save(data);
		return rtn;
	}
	@ResponseBody
	@PostMapping("/menu/delete")
	public ResultData menuDelete(@RequestBody t_menu data)
	{
		ResultData rtn = GlobalDao.MenuDao.delete(data);
		return rtn;
	}
}
