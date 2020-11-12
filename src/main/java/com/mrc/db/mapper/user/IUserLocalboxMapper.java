package com.mrc.db.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mrc.db.dto.user.userLocalboxDetail;
import com.mrc.db.dto.user.userLocalboxList;
import com.mrc.db.dto.user.userSearchCond;

@Mapper
public interface IUserLocalboxMapper {
	List<userLocalboxList> getList(userSearchCond cond);
	userLocalboxDetail getDetail(userSearchCond cond);
}
