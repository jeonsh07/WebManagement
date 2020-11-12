package com.mrc.db.mapper.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 사용자 글로벌 맵퍼
 * @author jeon.sh
 *
 */
@Component
public class GlobalUserMapper {
	public static IUserBannerMapper UserBanner;
	public static IUserLocalboxMapper UserLocalboxMapper;
	public static IUserCommonMapper UserCommonMapper; 
	@Autowired
	private GlobalUserMapper(IUserBannerMapper userbanner
			                 ,IUserLocalboxMapper userLocalboxMapper
			                 ,IUserCommonMapper userCommonMapper
			       ) {
		UserBanner = userbanner;
		UserLocalboxMapper = userLocalboxMapper;
		UserCommonMapper = userCommonMapper;
	}
}
