/**
 * 
 */
package com.mrc.db.mapper;

import com.mrc.db.dto.member.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 회원 테이블 맵퍼
 * @author jsh
 *
 */
@Mapper
public interface IMemberMapper {
	void save(t_member data);
	void saveMemberCompany(t_member data);
	List<t_member> getList(member_cond cond);
	List<t_member_company> getCompanyList(member_cond cond);
	List<memberList> getSearchList(member_cond cond);
	void remove(t_member data);
	void update(t_member data);
	void changeToken(t_member data);
	void changePassword(member_cond data);
	Integer dupConfirm(t_member data);
	Integer dupConfirmCompany(t_member_company data);
	List<t_business_type> getBusinessType(business_type_cond cond);
	Integer memberAuthSave(t_member_auth data);
	void memberAuthUpdate(t_member_auth data);
	List<t_member_auth> getMemberAuthNumber(t_member_auth cond);
	void myInfoUpdate(memberInfo param);
}
