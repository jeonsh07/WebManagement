package com.mrc.db.mapper;

import com.mrc.db.dto.keyword.keyword_cond;
import com.mrc.db.dto.keyword.t_keyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @Author: kyh
 * @Date: 2020. 4. 2.
 */
@Mapper
public interface IKeywordMapper {
	List<t_keyword> getList(keyword_cond cond);
	List<t_keyword> getAutoList(keyword_cond cond);
	void save(t_keyword data);
}
