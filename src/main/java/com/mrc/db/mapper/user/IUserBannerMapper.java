package com.mrc.db.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mrc.db.dto.user.userBannerDetail;
import com.mrc.db.dto.user.userBannerList;
import com.mrc.db.dto.user.userSearchCond;

@Mapper
public interface IUserBannerMapper {
	List<userBannerList> getList(userSearchCond cond);
	userBannerDetail getDetail(userSearchCond cond);
}
