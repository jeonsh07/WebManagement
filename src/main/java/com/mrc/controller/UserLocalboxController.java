package com.mrc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mrc.db.dao.user.GlobalUserDao;
import com.mrc.db.dto.user.userLocalboxDetail;
import com.mrc.db.dto.user.userSearchCond;

@RestController
public class UserLocalboxController {
	@ResponseBody
	@PostMapping("/user/getlocalboxdetail")
	public userLocalboxDetail getDetail(@RequestBody userSearchCond cond) {
		return GlobalUserDao.UserLocalboxDao.getDetail(cond);
	}
}
