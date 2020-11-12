package com.mrc.db.dao;

import com.mrc.db.dao.impl.IMenuDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.dto.menu.menu_cond;
import com.mrc.db.dto.menu.t_menu;
import com.mrc.db.mapper.GlobalMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MenuDao  implements IMenuDao<menu_cond, t_menu> {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_menu> getList(menu_cond cond) {
		// TODO Auto-generated method stub
		List<t_menu> rtn = GlobalMapper.MenuMapper.getList(cond); 
		return rtn;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public t_menu getData(menu_cond cond) {
		// TODO Auto-generated method stub
		List<t_menu> list = GlobalMapper.MenuMapper.getList(cond);
		if(list.size() == 1) return list.get(0);
		return list.get(0);
	}

	@Transactional
	@Override
	public ResultData saveList(List<t_menu> list) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			
			for (int i = 0; i < list.size(); i++) {
			    
				GlobalMapper.MenuMapper.save(this.setSaveParam(list.get(i)));
			}
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}
	
	private t_menu setSaveParam(t_menu data) {
		data.setMenu_level((int) Math.ceil(data.getSearch_code().length() / 3.00));
		if(data.getSearch_code().length() == 3)
		{
			data.setSearch_code(data.getSearch_code().replace("|", ""));
		}
		return data;
	}

	@Transactional
	@Override
	public ResultData save(t_menu data) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			// TODO Auto-generated method stub
			GlobalMapper.MenuMapper.save(this.setSaveParam(data));
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();

		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData delete(t_menu data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.MenuMapper.delete(data);
			rtn = ResultData.builder().enResultType(enResultType.Info).data(data.getMenu_code().toString()).build();
		} catch (Exception ex) {
			rtn = ResultData.builder().enResultType(enResultType.Error).message(ex.getMessage()).build();
		}
		return rtn;
	}
}
