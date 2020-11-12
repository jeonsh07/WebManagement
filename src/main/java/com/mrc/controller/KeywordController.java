package com.mrc.controller;

import com.mrc.db.dao.GlobalDao;
import com.mrc.db.dto.common.KoreanUnit;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.keyword.keyword_cond;
import com.mrc.db.dto.keyword.t_keyword;
import com.mrc.framework.Global;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author: kyh
 * @Date: 2020. 4. 2.
 */
@Controller
public class KeywordController {
	@ResponseBody
	@PostMapping("/temp/getkeyword")
	public List<t_keyword> getKeyword(@RequestBody keyword_cond cond) {
		cond.setSort_column_1("search_cnt");
		cond.setSort_column_2("keyword_name");
		cond.setLimit(10);
		
		return GlobalDao.KeywordDao.getList(cond);
	}

    @ResponseBody
    @PostMapping(value="/keyword/getautolist")
	public List<t_keyword> getAutoList(@RequestBody keyword_cond cond){
		if(Global.DataInfo.isNullEmpty(cond.getKeyword_name())) return new ArrayList<t_keyword>();
		KoreanUnit keyword = Global.DataInfo.convertKoreanUnit(cond.getKeyword_name());
		cond.setChosung(keyword.getChoSung());
		cond.setKeyword_units(keyword.getKeywordUnit());
		
		cond.setLimit(10);
		
		return GlobalDao.KeywordDao.getAutoList(cond);
	}
	@ResponseBody
	@PostMapping("/temp/savekeyword")
	public ResultData save(@RequestBody t_keyword data) {
		if(Global.LoginInfo.getIsLogin() == true) {
			data.setInsert_code(Global.LoginInfo.getMember().getMember_code());
		}
		else {
			data.setInsert_code(1);
		}
		return GlobalDao.KeywordDao.save(data);
	}
}
