package com.mrc.db.dao.impl;

import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.member.*;

import java.util.List;

/**
 * @Author: jsh,kyh
 * @Date: 2020. 6. 10.
 */

public interface IMemberDao<member_cond, t_member, memberList> extends IBaseDao<member_cond, t_member> {
	/**
	 * 패스워드변경
	 * 전상훈
	 * @param data
	 * @return
	 */
	 ResultData changePassword(member_cond data);
	/**
	 * 토큰정보 변경
	 * 전상훈
	 * @param data
	 * @return
	 */
	 ResultData changeToken(t_member data);
	 List<memberList> getSearchList(member_cond cond);
	 Integer dupConfirm(t_member data);
	 Integer dupConfirmCompany(t_member_company data);
	 List<t_business_type> getBusinessType(business_type_cond cond);
	/**
	 * 패스워드 인증 정보가져오기
	 * 2020.06.17 추가  - 전상훈
	 * @param cond
	 * @return
	 */
	 List<t_member_auth> getMemberAuthNumber(t_member_auth cond);
	/**
	 * 패스워드 인증 정보저장하기
	 * 2020.06.17 추가  - 전상훈
	 * @param cond
	 * @return
	 */
	 ResultData memberAuthSave(t_member_auth data);
	/**
	 * 인증 확인
	 * 2020.06.17추가 - 전상훈
	 * @param data
	 * @return
	 */
	ResultData memberAuthConfirm(t_member_auth data);
	
	/**
	 * 인증메일 보내기
	 * @param cond
	 * @return
	 */
	 ResultData resetPasswordEmail(member_cond cond);

	/**
	 * myinfo > 사용자 정보 업데이트
	 * @param param
	 * @return
	 */
	ResultData myInfoUpdate(memberInfo param);

}
