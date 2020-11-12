package com.mrc.db.dao.impl;

import java.util.List;

import com.mrc.db.dto.address.location_dong;
import com.mrc.db.dto.address.t_address_location1;
import com.mrc.db.dto.common.ResultData;

public interface IAddressLocationDao<address_location_cond,t_address_location> extends IBaseDao<address_location_cond,t_address_location> {
	public List<location_dong> getCurrentDong(address_location_cond cond);
	ResultData addressLocation1Save(t_address_location1 data);
}
