package com.mrc.db.mapper;

import com.mrc.db.dto.address.t_address_area;
import com.mrc.db.dto.common.SelectItem;
import com.mrc.db.dto.common.common_cond;
import com.mrc.db.dto.common.t_common;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 공통코드 테이블 맵퍼
 * @author jsh
 *
 */
@Mapper
public interface ICommonMapper {
	List<t_common> getList(common_cond cond);
	List<SelectItem> getSelectItemList(common_cond cond);
	void save(t_common data);
	void delete(t_common data);
	void update(t_common data);
	List<t_address_area> getAddressAreaList(t_address_area cond);
	List<String> getTableToHtml();
} 
