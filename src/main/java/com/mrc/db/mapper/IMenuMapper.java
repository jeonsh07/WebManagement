package com.mrc.db.mapper;

import com.mrc.db.dto.menu.menu_cond;
import com.mrc.db.dto.menu.t_menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;



@Mapper
public interface IMenuMapper {
	void save(t_menu data);
	List<t_menu> getList(menu_cond cond);
	void delete(t_menu data);
	void update(t_menu data);
	
}

