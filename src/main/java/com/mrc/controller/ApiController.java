/**
 * 
 */
package com.mrc.controller;

import com.mrc.db.dao.GlobalDao;
import com.mrc.db.dto.common.Kakaomap_cond;
import com.mrc.db.dto.member.member_cond;
import com.mrc.db.dto.member.t_member;
import com.mrc.framework.Global;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author jsh
 *
 */

@RestController

public class ApiController {


	@GetMapping(path = "/localtime")
	public String localtime() {
		//return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		return "안녕이라고 말하지마";
	}
	@GetMapping(path = "/localtime3")
	public String localtime3() {
		//return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		return "안녕이라고 말하지마3";
	}


	@GetMapping(path = "/api/helloWorld")
	public String helloWorld() {
		
		// return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		return String.format("%s %s", GlobalDao.ApiDao.selectName(),
				LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	}

	@GetMapping(path = "/member/memberlist")
	
	public List<t_member> memberlist() {
		List<t_member> list = GlobalDao.MemberDao.getList(member_cond.builder()
				//.member_code(1)
				//.member_id("mrc0700@gmail.com")
				//.member_pw("1111")
				.build());
	
		
		// return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		return list;
	}



    /* 위경도로 주소 정보 가져오기*/
    @ResponseBody
    @PostMapping(value="/kakaoapi/GetCoord2address")
    public ResponseEntity<String> GetCoord2address(@RequestBody Kakaomap_cond cond)
    {
    	ResponseEntity<String> rtn = Global.KakaoApi.call(URI.create("https://dapi.kakao.com/v2/local/geo/coord2address.json?x=" + cond.getLongi() + "&y=" + cond.getLat()));
    	return rtn;
	}
    
    /* 위경도로 주소 행정구역 가져오기*/
    @ResponseBody
    @PostMapping(value="/kakaoapi/coord2regioncode")
    public ResponseEntity<String> coord2regioncode(@RequestBody Kakaomap_cond cond)
    {
    	ResponseEntity<String> rtn = Global.KakaoApi.call(URI.create("https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=" + cond.getLongi() + "&y=" + cond.getLat())); 
    	return rtn;
	}
    
    
    
    /* 주소로  정보 가져오기*/
    @ResponseBody
    @PostMapping(value="/kakaoapi/address")
    public ResponseEntity<String> kakaoaddress(@RequestBody Kakaomap_cond cond)
    {
    	cond.setPage(cond.getPage() == null ? 1 : cond.getPage());
    	cond.setSize(cond.getSize() == null ? 10 : cond.getSize());
  
    	try {
    		cond.setQuery(	URLEncoder.encode(cond.getQuery(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    	 ResponseEntity<String> rtn = Global.KakaoApi.call(URI.create("https://dapi.kakao.com/v2/local/search/address.json?query=" + cond.getQuery() + "&page=" + Integer.toString(cond.getPage()) + "&size=" + Integer.toString(cond.getSize())));
    	return rtn;
	}
}
