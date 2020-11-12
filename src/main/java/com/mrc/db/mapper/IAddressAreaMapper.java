package com.mrc.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mrc.db.dto.address.address_cond;
import com.mrc.db.dto.address.location_dong;
import com.mrc.db.dto.address.t_address_area;

@Mapper
public interface IAddressAreaMapper {

	List<t_address_area> getList(t_address_area cond);
	void save(t_address_area data);
	void delete(t_address_area data);
	void update(t_address_area data);
	List<location_dong> getCodeByAddress(address_cond cond);
} 
