package com.mrc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mrc.db.dto.user.userAlamMsgCond;
import com.mrc.db.dto.user.userAlamMsgData;
import com.mrc.db.mapper.user.GlobalUserMapper;
import com.mrc.framework.Global;

@RestController
public class UserCommonController {
	@ResponseBody
	@PostMapping("/usercommon/getalammsglist")
	public List<userAlamMsgData> getAlamMsgList(@RequestBody userAlamMsgCond cond) {
		// TODO Auto-generated method stub
		if(Global.LoginInfo.getIsLogin())
		{
			cond.setMember_code(Global.LoginInfo.getMember().getMember_code());
			return GlobalUserMapper.UserCommonMapper.getAlamMsgList(cond);
		}else return null;
	}
}
