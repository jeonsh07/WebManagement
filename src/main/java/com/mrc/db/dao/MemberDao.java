/**
 * 
 */
package com.mrc.db.dao;

import com.mrc.db.dao.impl.IMemberDao;
import com.mrc.db.dto.common.MailData;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.dto.member.*;
import com.mrc.db.mapper.GlobalMapper;
import com.mrc.framework.Global;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author jsh
 *
 */
@Repository
public class MemberDao implements IMemberDao<member_cond, t_member, memberList> {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_member_auth> getMemberAuthNumber(t_member_auth cond) {
		// TODO Auto-generated method stub
		return GlobalMapper.MemberMapper.getMemberAuthNumber(cond);
	}

	@Transactional
	@Override
	public ResultData memberAuthSave(t_member_auth data) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		if (data.getInsert_code() == null)
			data.setInsert_code(data.getMember_code());
		Integer nIdx = GlobalMapper.MemberMapper.memberAuthSave(data);
		rtn.setData(Integer.toString(nIdx));
		return rtn;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_member> getList(member_cond cond) {
		if (!StringUtils.isBlank(cond.getMember_pw()))
			cond.setMember_pw(Global.SecurityInfo.encryptSHA256(cond.getMember_pw()));
		List<t_member> list = GlobalMapper.MemberMapper.getList(cond);
		// return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		return list;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<memberList> getSearchList(member_cond cond) {
		// TODO Auto-generated method stub

		return GlobalMapper.MemberMapper.getSearchList(cond);

	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public t_member getData(member_cond cond) {
		if (!StringUtils.isBlank(cond.getMember_pw()))
			cond.setMember_pw(Global.SecurityInfo.encryptSHA256(cond.getMember_pw()));
		List<t_member> list = GlobalMapper.MemberMapper.getList(cond);
		if (list.size() == 1) {
			return list.get(0);
		} else
			return null;
	}

	@Transactional
	@Override
	public ResultData saveList(List<t_member> list) {

		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();

		try {
			for (int i = 0; i < list.size(); i++) {

				// list.get(i).setInsert_code();
				GlobalMapper.MemberMapper.save(list.get(i));
			}
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData save(t_member data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			// TODO Auto-generated method stub
			if (data.getCompany_name() == null) {
				GlobalMapper.MemberMapper.save(data);
			} else {
				GlobalMapper.MemberMapper.saveMemberCompany(data);
			}

			rtn.setData(Integer.toString(data.getMember_code()));

			if (Global.LoginInfo.getIsLogin() == true) {
				Global.LoginInfo.setMember(data);
			}

		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData changePassword(member_cond data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			// TODO Auto-generated method stub
			GlobalMapper.MemberMapper.changePassword(data);
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();

		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData changeToken(t_member data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			// TODO Auto-generated method stub
			GlobalMapper.MemberMapper.changeToken(data);
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData resetPasswordEmail(member_cond cond) {
		// return GlobalDao.MemberDao.GetList(cond);
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		
		List<t_member> list = GlobalDao.MemberDao.getList(member_cond.builder().member_id(cond.getMember_id()).build());
		if (list.size() == 1) {
			t_member member = list.get(0);
			if(!member.getMember_name().equals(cond.getMember_name()))
			{
				rtn.enResultType = enResultType.Error;
				rtn.data = "name";
				rtn.message = "*입력하신 이름이 없습니다. </br>&nbsp;다시 한번 확인해 주세요.";
			}else {
			String rtnNumber = Global.DataInfo.getGenerateNumber(6);
			String message = rtnNumber;
			
			t_member_auth data = t_member_auth.builder().member_code(member.getMember_code())
					.limit_time(Global.DataInfo.addHoursToTimestamp(new Date(), 1)).build();
			GlobalMapper.MemberMapper.memberAuthUpdate(data);
			data = t_member_auth.builder().member_code(member.getMember_code()).auth_number(Integer.parseInt(rtnNumber))
					.limit_time(Global.DataInfo.addHoursToTimestamp(new Date(), 1)).build();
			GlobalMapper.MemberMapper.memberAuthSave(data);

			
			MailData mail = MailData.builder().toMail("mrc0700@gmail.com").title("[온다지] 요청하신 인증번호를 알려드립니다.")
					.message(message).build();
			Global.MailInfo.sendMail(mail);
			rtn.setData(Integer.toString(list.get(0).getMember_code()));
			}
			
		} else {
			rtn.enResultType = enResultType.Error;
			rtn.data = "email";
			rtn.message = "*입력하신 이메일 주소가 없습니다. </br>&nbsp;다시 한번 확인해 주세요.";
		}
		return rtn;
	}



	@Transactional
	@Override
	public ResultData memberAuthConfirm(t_member_auth data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		// TODO Auto-generated method stub
		List<t_member_auth> list = this.getMemberAuthNumber(data);
		if (list.size() == 1) {
			data = list.get(0);
			if (data.getLimit_time().getTime() > Timestamp.valueOf(LocalDateTime.now()).getTime()) {
				if (data.getInsert_code() == null)
					data.setInsert_code(data.getMember_code());
				// data.setAuth_time(Timestamp.valueOf(LocalDateTime.now()));
				GlobalMapper.MemberMapper.memberAuthUpdate(data);
			} else {
				rtn.setEnResultType(enResultType.Error);
				rtn.setMessage("인증기간이 만료되었습니다.\n다시 인증요청을 해주세요!");
			}
		} else {
			rtn.setEnResultType(enResultType.Error);
			rtn.setMessage("인증정보가 존재하지 않습니다.");
		}

		return rtn;
	}

	@Override
	public Integer dupConfirm(t_member data) {
		return GlobalMapper.MemberMapper.dupConfirm(data);
	}

	@Override
	public Integer dupConfirmCompany(t_member_company data) {
		return GlobalMapper.MemberMapper.dupConfirmCompany(data);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_business_type> getBusinessType(business_type_cond cond) {
		return GlobalMapper.MemberMapper.getBusinessType(cond);
	}

	@Transactional
	@Override
	public ResultData myInfoUpdate(memberInfo param) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.MemberMapper.myInfoUpdate(param);
		}catch(Exception ex)
		{
			rtn.setEnResultType(enResultType.Error);
			rtn.setMessage(ex.getMessage());
		}
		return rtn;
	}
}
