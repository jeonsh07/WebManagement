/**
 * 
 */
package com.mrc.db.dao.impl;

import java.util.List;

import com.mrc.db.dto.address.t_address_area;
import com.mrc.db.dto.common.ResultData;

/**
 * 공통 Dao
 * @author jsh
 *
 * @param <common_cond>
 * @param <t_common>
 * @param <SelectItem>
 */
public interface ICommonDao<common_cond, t_common, SelectItem> extends IBaseDao<common_cond, t_common>  {
	/**
	 * 공통 Selects Item정보 가져오기
	 * @param cond
	 * @return
	 */
	public List<SelectItem> getSelectItemList(common_cond cond);
	
	public List<t_address_area> getAddressAreaList(t_address_area cond);
	
	public List<String> getTableToHtml();
	
	public ResultData delete(t_common data);
}
