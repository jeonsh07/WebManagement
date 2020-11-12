package com.mrc.db.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mrc.db.dto.user.userAlamMsgCond;
import com.mrc.db.dto.user.userAlamMsgData;

@Mapper
public interface IUserCommonMapper {
	List<userAlamMsgData> getAlamMsgList(userAlamMsgCond cond);
}
