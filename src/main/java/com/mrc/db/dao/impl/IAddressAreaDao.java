package com.mrc.db.dao.impl;

import java.util.List;

import com.mrc.db.dto.address.address_cond;
import com.mrc.db.dto.address.location_dong;
import com.mrc.db.dto.common.Kakaomap_cond;
import com.mrc.db.dto.common.ResultData;

public interface IAddressAreaDao <t_address_area> extends IBaseDao<t_address_area, t_address_area> {
	ResultData update(t_address_area data);
	List<location_dong> getCodeByAddress(address_cond cond);
	List<location_dong> getAddressAreaBylocation(Kakaomap_cond cond);
}
