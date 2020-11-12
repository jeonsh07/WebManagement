package com.mrc.db.mapper;

import com.mrc.db.dto.signage.signage_cond;
import com.mrc.db.dto.signage.t_signage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ISignageMapper {
	void save(t_signage data);
	List<t_signage> getList(signage_cond cond);
	void delete(t_signage data);
	void update(t_signage data);
}
