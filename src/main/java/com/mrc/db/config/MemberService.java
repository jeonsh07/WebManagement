package com.mrc.db.config;

import com.mrc.db.dao.impl.IMemberDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.member.memberList;
import com.mrc.db.dto.member.member_cond;
import com.mrc.db.dto.member.t_member;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

	private IMemberDao<member_cond, t_member, memberList> memberRepository;

	@Transactional
	public ResultData joinUser(t_member member) {
		List<t_member> list = new ArrayList<t_member>();
		list.add(member);
		ResultData rtn = memberRepository.saveList(list);
		
		return rtn;
	}

	@Override
	public UserDetails loadUserByUsername(String member_id) throws UsernameNotFoundException {

		t_member userEntity = memberRepository.getData(member_cond.builder().member_id(member_id).build());

		List<GrantedAuthority> authorities = new ArrayList<>();

		if (("admin@altsoft.kr").equals(member_id)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}

		return new User(userEntity.getMember_id(), userEntity.getMember_pw(), authorities);
	}
}
