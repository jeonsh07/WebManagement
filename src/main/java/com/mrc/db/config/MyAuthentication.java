/**
 * 
 */
package com.mrc.db.config;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.mrc.db.dto.member.t_member;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jsh
 *
 */
@Setter @Getter
public class MyAuthentication extends UsernamePasswordAuthenticationToken{
	private static final long serialVersionUID = 1L;
	
	public t_member member;
	
	public MyAuthentication(String id, String pw, List<GrantedAuthority> authList, t_member member) {
		super(id, pw, authList);
		this.member = member;
	}
}