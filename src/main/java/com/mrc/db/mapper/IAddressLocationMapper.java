package com.mrc.db.mapper;

import com.mrc.db.dto.address.address_location_cond;
import com.mrc.db.dto.address.location_dong;
import com.mrc.db.dto.address.t_address_location;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface IAddressLocationMapper {
	List<t_address_location> getList(address_location_cond cond);
	void save(t_address_location data);
	void remove(t_address_location data);
	void update(t_address_location data);
	void updatelatLongiList(List<t_address_location> list);
	List<location_dong> getCurrentDong(address_location_cond cond);
}
