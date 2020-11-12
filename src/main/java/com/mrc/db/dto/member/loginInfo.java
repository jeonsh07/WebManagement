package com.mrc.db.dto.member;

import com.mrc.db.dto.menu.t_menu;
import com.mrc.framework.Global;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class loginInfo {
	@Default
	private Boolean isLogin = false;
	@Default
	private enAuthType auth_type = enAuthType.Member;
	private List<t_menu> menuList;
	private List<t_menu> userMenuList;
	private t_member member;

	

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}

	public void setLogin(t_member member) {
		this.setLogin(member, null);
	}

	public void setLogin(t_member member, List<t_menu> menuList) {
		Global.LoginInfo.setIsLogin(true);
		Global.LoginInfo.setMember(member);
		Global.LoginInfo.setMenuList(menuList.stream().filter(f->f.getProject_type().equals(1)).collect(Collectors.toList()));
		Global.LoginInfo.setUserMenuList(menuList.stream().filter(f->f.getProject_type().equals(2)).collect(Collectors.toList()));
		Global.LoginInfo.setAuth_type(enAuthType.IntToEnum(member.getAuth_type()));

		session().setAttribute("userInfo", Global.LoginInfo);
	}

	public void setLogin(t_member member, List<t_menu> menuList, Authentication authentication) {
		Global.LoginInfo.setIsLogin(true);
		Global.LoginInfo.setMember(member);
		Global.LoginInfo.setMenuList(menuList.stream().filter(f->f.getProject_type().equals(1)).collect(Collectors.toList()));
		Global.LoginInfo.setUserMenuList(menuList.stream().filter(f->f.getProject_type().equals(2)).collect(Collectors.toList()));
		Global.LoginInfo.setAuth_type(enAuthType.IntToEnum(member.getAuth_type()));
		session().setAttribute("userInfo", Global.LoginInfo);
	}

	public void setLoginOut() {
		
		session().setAttribute("userInfo", null);
		Global.LoginInfo.setIsLogin(false);
		Global.LoginInfo.setAuth_type(enAuthType.Member);
		Global.LoginInfo.setMember(null);
		Global.LoginInfo.setMenuList(null);
	}

	public void setLoginOut(Authentication authentication) {
		session().setAttribute("userInfo", null);
		Global.LoginInfo.setIsLogin(false);
		Global.LoginInfo.setAuth_type(enAuthType.Member);
		Global.LoginInfo.setMember(null);
		Global.LoginInfo.setMenuList(null);
		
	}

	public Boolean isAdmin() {
		return Global.LoginInfo.getAuth_type() == enAuthType.Admin;
	}

	public Boolean isAdMember() {
		return Global.LoginInfo.getAuth_type() == enAuthType.AdMember;
	}

	public Boolean isLocalMember() {
		return Global.LoginInfo.getAuth_type() == enAuthType.LocalMember;
	}

	public Boolean isMember() {
		return Global.LoginInfo.getAuth_type() == enAuthType.Member;
	}

}
