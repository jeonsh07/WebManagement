/**
 * 
 */
package com.mrc.framework;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mrc.db.dto.member.loginInfo;




/**
 * @author jsh
 *
 */
@Component
public class Global {

	public static CommonInfo CommonInfo;
	public static SecurityInfo SecurityInfo;
	public static DataInfo DataInfo;
	public static FileInfo FileInfo;
	public static loginInfo LoginInfo;
	public static MailInfoBase MailInfoBase;
	@SuppressWarnings("rawtypes")
	public static MailInfo MailInfo;
	public static KakaoApi KakaoApi;
	//public static ExcelInfo<T> ExcelInfo;
	public static ConfigInfo ConfigInfo;
	public static MapInfo MapInfo;
	
	public static NumberInfo NumberInfo;
	
	public static ExcelParser ExcelParser;
	
	
	@Autowired
	public MailInfoBase mailInfoBase;
	@SuppressWarnings("rawtypes")
	@Autowired
	public MailInfo maininfo;
	
	@PostConstruct
	private void initialize() {
		CommonInfo = new CommonInfo();
		SecurityInfo = new SecurityInfo();
		DataInfo = new DataInfo();
		FileInfo = new FileInfo();
		LoginInfo = new loginInfo();
		KakaoApi = new KakaoApi();
		MailInfoBase = this.mailInfoBase;
		MailInfo = this.maininfo;
		//ExcelInfo = new ExcelInfo<T>();
		ConfigInfo = new ConfigInfo();
		MapInfo = new MapInfo();
		NumberInfo = new NumberInfo();
		ExcelParser = new ExcelParser();
	}
}
