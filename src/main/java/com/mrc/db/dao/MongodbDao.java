package com.mrc.db.dao;

import com.mrc.db.dao.impl.IMongodbDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.T_AD_PLAY_LOG_MONGO;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.dto.mongoDb.*;
import com.mrc.framework.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MongodbDao implements IMongodbDao<log_cond, t_log>{

	@Autowired
	private MongoTemplate mongoTemplate;
	

	public List<T_AD_PLAY_LOG_MONGO> getAdLog() {
		Query query = new Query().addCriteria(Criteria.where("DEVICE_NAME").is(""));
		List<T_AD_PLAY_LOG_MONGO> rtn = mongoTemplate.find(query, T_AD_PLAY_LOG_MONGO.class);

		return rtn;
	}
	
	
	private Query setLogCond(log_cond cond)
	{
		Query query = new Query();
		
		if (cond.getMember_code() != null)
			query =query.addCriteria(Criteria.where("member_code").in(cond.getMember_code()));
		if (cond.getMember_name() != null)
			query = query.addCriteria(Criteria.where("member_name").in(cond.getMember_name()));
		//if (!Global.DataInfo.isNullEmpty(cond.getFr_date()))
		//	query = query.addCriteria(Criteria.where("fr_date").is(cond.getFr_date()));
		//if (!Global.DataInfo.isNullEmpty(cond.getTo_date()))
		//	query = query.addCriteria(Criteria.where("reg_date").(cond.getTo_date()));
		if (!Global.DataInfo.isNullEmpty(cond.getDevice_type()))
			query = query.addCriteria(Criteria.where("device_type").in(cond.getDevice_type()));
		
		if (!Global.DataInfo.isNullEmpty(cond.getSearchText()))
		{
			String searchText = cond.getSearchText();
			query = query.addCriteria(new Criteria()
										.orOperator(Criteria.where("member_code").in(searchText))
										.orOperator(Criteria.where("member_name").in(searchText))
										.orOperator(Criteria.where("page_url").in(searchText))
										.orOperator(Criteria.where("page_name").in(searchText))
										.orOperator(Criteria.where("device_type").in(searchText))
										.orOperator(Criteria.where("reg_date").in(searchText))
					
					);
		}
		return query;
	}
	
	@Override
	public long getLogCount(log_cond cond) {
		// TODO Auto-generated method stub
		Query query = this.setLogCond(cond);
		return mongoTemplate.count(query, t_log.class);
	}
	
	@Override
	public List<t_log> getList(log_cond cond) {
		// TODO Auto-generated method stub
		//Criteria param = Criteria.where("1").is("1");
		Query query = this.setLogCond(cond);
		int nStart = (int)((cond.getPage() -1)* cond.getPage_size()) ;
		int nLimit = cond.getPage_size();
		
		Sort sort = Sort.by(Sort.Direction.DESC, cond.getSort());
		query.with(sort);
		query = query.skip(nStart).limit(nLimit);

		List<t_log> list = mongoTemplate.find(query, t_log.class);
		return list;
	}

	@Override
	public t_log getData(log_cond cond) {
		// TODO Auto-generated method stub
		return this.getList(cond).get(0);
	}

	@Override
	public ResultData save(t_log data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			if (Global.LoginInfo.getIsLogin() == true) {
				data.setMember_code(Global.LoginInfo.getMember().getMember_code());
				data.setMember_name(Global.LoginInfo.getMember().getMember_name());
			} else
				data.setMember_code(1);
			data.setReg_date(new Date());
			mongoTemplate.insert(data);
		} catch (Exception ex) {
			rtn.setMessage(ex.getMessage());
		}
		return rtn;
	}

	@Override
	public ResultData saveList(List<t_log> list) {
		// TODO Auto-generated method stub
		return null;
	}
	public ResultData delete(t_log data) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ResultData logAdPlaceSave(List<log_adPlace> list) {
		// TODO Auto-generated method stub
		
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			long timeInMillis =System.currentTimeMillis();
			
			//if(!Global.LoginInfo.isAdmin())
			//{
				Boolean bInsert = false;
				for(int i=0; i<list.size(); i++)
				{
					if(list.get(i).getIdx() == null || list.get(i).getIdx() <= 0)
					{
						bInsert = true;
						list.get(i).setIdx(timeInMillis);
						list.get(i).setIdx_sub(i);
						if(Global.LoginInfo.getIsLogin())
						{
							list.get(i).setMember_code(Global.LoginInfo.getMember().getMember_code());
							list.get(i).setMember_name(Global.LoginInfo.getMember().getMember_name());
						}
						else {
							list.get(i).setMember_code(-1);
						}
						list.get(i).setReg_date(new Date());
					}
				}
				if(bInsert)
					mongoTemplate.insertAll(list);
				else {
					Query query = new Query();
				 	query.addCriteria(Criteria.where("idx").is(list.get(0).getIdx()));
				 	Update update = new Update();
			        update.set("reg_date", new Date());
			        update.set("idx", timeInMillis);
			        mongoTemplate.updateMulti(query, update, log_adPlace.class);
				}
			//}
		} catch (Exception ex) {
			rtn.setMessage(ex.getMessage());
		}
		return rtn;
	}


	@Override
	public List<log_adPlace> getLogAdPlaceList(log_adPlace_cond cond) {
		// TODO Auto-generated method stub
		
		if(!Global.LoginInfo.isAdmin() )
		{
			if( Global.LoginInfo.getIsLogin()) cond.setMember_code(Global.LoginInfo.getMember().getMember_code());
			else  cond.setMember_code(-1);
		}else {
			cond.setMember_code( cond.getMember_code() == null ? Global.LoginInfo.getMember().getMember_code() : cond.getMember_code());
		}
		
		Query query = new Query();
		
		if (cond.getMember_code() != null)
			query =query.addCriteria(Criteria.where("member_code").exists(true).in(cond.getMember_code()));
	
		
		Sort sort = Sort.by(Sort.Direction.DESC, cond.getSort());
		query.with(sort);
		int nStart = (int)((cond.getPage() -1)* cond.getPage_size());
		query = query.skip(nStart).limit(cond.getPage_size());
		
		List<log_adPlace> list = mongoTemplate.find(query, log_adPlace.class);
		return list;
	}


	@Override
	public ResultData logKeywordSave(log_keyword keyword) {
		// TODO Auto-generated method stub
		
	
		
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
		
			
			List<log_keyword> list = new ArrayList<log_keyword>();
			list.add(keyword);
			mongoTemplate.insertAll(list);
			
		}catch(Exception ex) {
			rtn.setEnResultType(enResultType.Error);
			rtn.setMessage(ex.getLocalizedMessage());
		}
		return rtn;
	}
}
