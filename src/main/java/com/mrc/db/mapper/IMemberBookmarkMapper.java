package com.mrc.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mrc.db.dto.member.t_member_bookmark;

@Mapper
public interface IMemberBookmarkMapper {
	List<t_member_bookmark> getList(t_member_bookmark cond);
	void save(t_member_bookmark data);
	void delete(t_member_bookmark data);
}
