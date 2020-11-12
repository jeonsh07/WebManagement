package com.mrc.db.mapper;

import com.mrc.db.dto.signage.signage_cond;
import com.mrc.db.dto.signage.t_signage_place;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ISignagePlaceMapper {
	void save(t_signage_place data);
	List<t_signage_place> getList(signage_cond cond);
	void delete(t_signage_place data);
}
