package com.mrc.db.dao.impl;

import java.util.List;

/**
 * @Author: kyh
 * @Date: 2020. 4. 2.
 */
public interface IKeywordDao<keyword_cond, t_keyword> extends IBaseDao<keyword_cond, t_keyword> {
	
	/**
	 * 키워드자동검색 (jsh)
	 * @param cond
	 * @return
	 */
	List<t_keyword> getAutoList(keyword_cond cond);
}
