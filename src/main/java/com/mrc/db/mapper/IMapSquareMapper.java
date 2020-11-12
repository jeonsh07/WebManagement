package com.mrc.db.mapper;

import com.mrc.db.dto.address.map_square_cond;
import com.mrc.db.dto.address.t_map_square;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMapSquareMapper {
	List<t_map_square> getList(map_square_cond cond);
	void save(t_map_square data);
	void remove(t_map_square data);
	void update(t_map_square data);
}
