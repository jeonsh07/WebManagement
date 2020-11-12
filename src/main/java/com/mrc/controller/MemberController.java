/**
 * 
 */
package com.mrc.controller;

import com.mrc.db.dao.GlobalDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.dto.member.*;
import com.mrc.db.dto.menu.menu_cond;
import com.mrc.db.dto.menu.t_menu;
import com.mrc.framework.ExcelInfo;
import com.mrc.framework.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author jsh
 *
 */

@Controller
public class MemberController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PersistentTokenBasedRememberMeServices rememberMeService;
	
	@RequestMapping(value = { "/member/login" }, method = RequestMethod.GET)
	public String login(@RequestParam("loginfail") Boolean loginfail) {
		return "/member/login";
	}

	/**
	 * 로그인 실행
	 * @param cond
	 * @param session
	 * @return
	 */
	@ResponseBody
	@PostMapping("/member/logindo")
	public loginInfo logindo(@RequestBody member_cond cond, HttpSession session) {
		loginInfo rtn = this.loginExec(cond, session);
		
		return rtn;
	}

	private loginInfo loginExec(member_cond cond, HttpSession session) {
		try {

			UsernamePasswordAuthenticationToken token
					= new UsernamePasswordAuthenticationToken(cond.getMember_id(), cond.getMember_pw());
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			ServletRequestAttributes ser = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ser.getRequest();
			HttpServletResponse response = ser.getResponse();
			
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
					SecurityContextHolder.getContext());
			t_member member = GlobalDao.MemberDao.getData(cond);
	
			if (member == null) {
				Global.LoginInfo.setLoginOut();
				if (authentication != null) {
					try {
						rememberMeService.loginFail(request, response);
					} catch (Exception ex) {}
				}
				return null;
			} else {
				Integer nAuth =  member.getAuth_type();
				List<t_menu> menus = GlobalDao.MenuDao.getList(menu_cond.builder().menu_auth(nAuth).project_type(-1).hide(false).build());
		
				Global.LoginInfo.setLogin(member, menus,authentication);
				if(cond.getRemember_me()) rememberMeService.loginSuccess(request, response, authentication);
			}
			return Global.LoginInfo;

		} catch (Exception ex) {
			throw ex;
		}
	}

	@RequestMapping(value = "/member/loginsubmit", method = RequestMethod.POST)
	public RedirectView loginSubmit(HttpServletRequest request,
            HttpServletResponse response, HttpSession session
            , @RequestParam("member_id") String member_id
            , @RequestParam("member_pw") String member_pw
            , @RequestParam("remember_me") Boolean remember_me) {
		
		member_cond cond = member_cond.builder()
				              .member_id(member_id)
				              .member_pw(member_pw)
				              .remember_me(remember_me).build();
		loginInfo rtn = this.loginExec(cond, session);
		
		RedirectView redirectView = new RedirectView();
	      
	   	if(rtn == null)
		{
			redirectView.setUrl("/member/login?loginfail=true");
		}else {
			redirectView.setUrl("/");;
		}
	    return redirectView;
	}

	@ResponseBody
	@PostMapping("/member/getmyinfo")
	public loginInfo getMyInfo(HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (session.getAttribute("userInfo") == null) {
			if(!(authentication == null || authentication.getPrincipal() == null)) {
				String member_id = (String) authentication.getPrincipal();
				t_member member = GlobalDao.MemberDao.getData(member_cond.builder().member_id(member_id).build());

				if (member == null)
					Global.LoginInfo.setLoginOut();
				else {
					Integer nAuth = (member.getAuth_type() == 1) ? null : member.getAuth_type();
					List<t_menu> menus = GlobalDao.MenuDao
							.getList(menu_cond.builder().menu_auth(nAuth).hide(false).build());
					Global.LoginInfo.setLogin(member, menus, authentication);
				}
			} else {
				Global.LoginInfo.setLoginOut(authentication);
			}
			return Global.LoginInfo;
		}
		try {
			return (loginInfo) session.getAttribute("userInfo");
		} catch (Exception ex) {
			Global.LoginInfo.setLoginOut(authentication);
			return Global.LoginInfo;
		}
	}

	@ResponseBody
	@PostMapping("/member/save")
	public ResultData save(@RequestBody t_member data) {
		data.setMember_pw(Global.SecurityInfo.encryptSHA256(data.getMember_pw_reg()));
		if (Global.LoginInfo.getIsLogin() == true)
			data.setInsert_code(Global.LoginInfo.getMember().getMember_code());
		else
			data.setInsert_code(1);

		return GlobalDao.MemberDao.save(data);
	}

	@ResponseBody
	@PostMapping("/member/GetSearchList")
	public List<memberList> GetSearchList(@RequestBody member_cond cond) {
		return GlobalDao.MemberDao.getSearchList(cond);
	}

	@ResponseBody
	@PostMapping("/member/GetList")
	public List<t_member> GetList(@RequestBody member_cond cond) {
		return GlobalDao.MemberDao.getList(cond);
	}

	@ResponseBody
	@PostMapping("/member/findID")
	public List<t_member> findID(@RequestBody member_cond cond) {
		List<t_member> list = GlobalDao.MemberDao.getList(cond);
		for (int i = 0; i < list.size(); i++) {
			String memberId = list.get(i).getMember_id();
			String[] arr = memberId.split("@");
			if (arr[0].length() >= 0) {
				memberId = arr[0].substring(0, 3).concat("***@").concat(arr[1]);
			} else {
				memberId = arr[0].substring(0, 1).concat("***@").concat(arr[1]);
			}

			list.get(i).setMember_id(memberId);
		}
		return list;

	}

	/**
	 * 패스워드변경 메일보내기
	 * @param cond
	 * @return
	 */
	@ResponseBody
	@PostMapping("/member/resetPasswordEmail")
	public ResultData resetPasswordEmail(@RequestBody member_cond cond) {
		// return GlobalDao.MemberDao.GetList(cond);
		ResultData rtn = GlobalDao.MemberDao.resetPasswordEmail(cond);
		return rtn;
	}

	/**
	 * 패스워드변경
	 * @param data
	 * @return
	 */
	@ResponseBody
	@PostMapping("/member/changepw")
	public ResultData changePw(@RequestBody member_cond data) {
		data.setMember_pw(Global.SecurityInfo.encryptSHA256(data.getMember_pw_reg()));
		if (Global.LoginInfo.getIsLogin() == true) {
			data.setInsert_code(Global.LoginInfo.getMember().getMember_code());
			if (data.getMember_code() == null) {
				data.setMember_code(Global.LoginInfo.getMember().getMember_code());
			}
		} else {
			return ResultData.builder().enResultType(enResultType.Error).message("로그아웃 상태입니다.").build();
		}
		return GlobalDao.MemberDao.changePassword(data);
	}

	/**
	 * 기존 패스워드 입력후 패스워드변경
	 * @param data
	 * @return
	 */
	@ResponseBody
	@PostMapping("/member/changepw2")
	public ResultData changePw2(@RequestBody member_cond data) {
		if (Global.LoginInfo.getIsLogin() == true) {
			data.setMember_code(Global.LoginInfo.getMember().getMember_code());
		}else{
			return ResultData.builder().enResultType(enResultType.Error).data("logout").message("로그아웃 되었습니다..").build();
		}

		List<t_member> rtnList=	GlobalDao.MemberDao.getList(data);
		if(rtnList.size()!=1)
		{
			return ResultData.builder().enResultType(enResultType.Error).data("notpassword").message("기존 패스워드를 잘못되었습니다.").build();
		}
		data.setMember_pw(Global.SecurityInfo.encryptSHA256(data.getMember_pw_reg()));

		if (Global.LoginInfo.getIsLogin() == true) {
			data.setInsert_code(Global.LoginInfo.getMember().getMember_code());
			if (data.getMember_code() == null)
			{
				data.setMember_code(Global.LoginInfo.getMember().getMember_code());
			}
		}
		else {
			return ResultData.builder().enResultType(enResultType.Error).message("로그아웃 상태입니다.").build();
		}
		return GlobalDao.MemberDao.changePassword(data);
	}

	/**
	 * 인증번호확인
	 * @param data
	 * @return
	 */
	@ResponseBody
	@PostMapping("/member/memberAuthConfirm")
	public ResultData memberAuthConfirm(@RequestBody t_member_auth data) {
		if (Global.LoginInfo.getIsLogin() == true)
			data.setInsert_code(Global.LoginInfo.getMember().getMember_code());
		else
			data.setInsert_code(data.getMember_code());
		return GlobalDao.MemberDao.memberAuthConfirm(data);
	}
	
	/**
	 * 북마크
	 * @param data
	 * @return
	 */
	@ResponseBody
	@PostMapping("/member/bookmarkSave")
	public ResultData bookmarkSave(@RequestBody t_member_bookmark data) {
		if (Global.LoginInfo.getIsLogin())
			data.setMember_code(Global.LoginInfo.getMember().getMember_code());
		else {
			return ResultData.builder().enResultType(enResultType.Error).message("로그인 정보가 없습니다.").build();
		}
		
		if(data.getIs_bookmark())
			return GlobalDao.MemberBookmarkDao.save(data);
		else
		{
			return GlobalDao.MemberBookmarkDao.delete(data);
		}
	}

	/**
	 * 회원정보 엑셀다운로드
	 * @param member_name
	 * @param response
	 */
	@RequestMapping("/member/excelDown")
	public void memberExcelDown(@RequestParam(required = false) String member_name, HttpServletResponse response) {

		List<memberList> list = GlobalDao.MemberDao
				.getSearchList(member_cond.builder().member_name(member_name).build());
		;

		String[] arrStr = { "코드", "아이디", "이름", "권한", "이미지", "이미지크기", "생년월일" };
		List<String> headers = Arrays.asList(arrStr);

		try {
			new ExcelInfo<memberList>(memberList.class).listToExcel(response, list, headers, "엑셀다운로드");
		} catch (Exception ex) {
		}
	}

	@ResponseBody
	@PostMapping("/comp/getCompName")
	public String getCompName() {

		return "알트소프트";
	}

	/**
	 * 로그아웃
	 * @param session
	 * @return
	 */
	@ResponseBody
	@PostMapping("/member/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Global.LoginInfo.setLoginOut();
		if (authentication != null) {
			ServletRequestAttributes ser = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ser.getRequest();
			HttpServletResponse response = ser.getResponse();
			try {
					rememberMeService.logout(request, response, authentication);
					if (authentication != null){ new SecurityContextLogoutHandler().logout(request, response, authentication); }
			} catch (Exception ex) {}
		}
		 return "";
	}

	@ResponseBody
	@PostMapping("/member/dupconfirm")
	public Integer dupConfirm(@RequestBody t_member data) {
		return GlobalDao.MemberDao.dupConfirm(data);
	}

	@ResponseBody
	@PostMapping("/member/dupconfirmcompany")
	public Integer dupConfirmCompany(@RequestBody t_member_company data) {
		return GlobalDao.MemberDao.dupConfirmCompany(data);
	}

	@ResponseBody
	@PostMapping("/member/getbusinesstype")
	public List<t_business_type> getBusinessType(@RequestBody business_type_cond cond) {
		return GlobalDao.MemberDao.getBusinessType(cond);
	}


	/**
	 * 북마크
	 * @param data
	 * @return
	 */
	@ResponseBody
	@PostMapping("/member/myInfoUpdate")
	public ResultData myInfoUpdate(@RequestBody memberInfo data) {

		if (Global.LoginInfo.getIsLogin() == true)
			data.setReg_code(Global.LoginInfo.getMember().getMember_code());

		ResultData rtn =  GlobalDao.MemberDao.myInfoUpdate(data);
		member_cond cond = member_cond.builder().member_code(Global.LoginInfo.getMember().getMember_code()).build();

		Global.LoginInfo.setMember(GlobalDao.MemberDao.getData(cond));
		return rtn;
	}
}
