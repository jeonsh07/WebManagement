package com.mrc.db.dao.impl;

import java.util.List;

import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.mongoDb.log_adPlace;
import com.mrc.db.dto.mongoDb.log_adPlace_cond;
import com.mrc.db.dto.mongoDb.log_keyword;

public interface IMongodbDao<log_cond,t_log> extends IBaseDao<log_cond,t_log> {
	long getLogCount(log_cond cond);
	ResultData logAdPlaceSave(List<log_adPlace> list);
	List<log_adPlace> getLogAdPlaceList(log_adPlace_cond cond);
	ResultData logKeywordSave(log_keyword keyword);
}
