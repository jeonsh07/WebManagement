package com.mrc.db.dao.impl;

import java.util.List;

import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.signage.t_signage_place;

public interface ISignageDao<signage_cond, t_signage> extends IBaseDao<signage_cond, t_signage> {
	ResultData delete(t_signage data);
	ResultData update(t_signage data);
	List<t_signage_place> getSignagePlace(signage_cond cond);
	ResultData signagePlaceSave(t_signage_place data);
	ResultData signagePlaceDelete(t_signage_place data);
}
