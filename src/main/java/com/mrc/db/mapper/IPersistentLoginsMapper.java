package com.mrc.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mrc.db.dto.member.persistent_logins;
@Mapper
public interface IPersistentLoginsMapper {
	List<persistent_logins> getTokenList(persistent_logins cond);
	void createToken(persistent_logins data);
	void updateToken(persistent_logins data);
	void deleteToken(persistent_logins data);
}
