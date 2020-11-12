package com.mrc.db.dao;

import com.mrc.db.dao.impl.IMemberBookmarkDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.dto.member.t_member_bookmark;
import com.mrc.db.mapper.GlobalMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MemberBookmarkDao implements IMemberBookmarkDao<t_member_bookmark> {

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<t_member_bookmark> getList(t_member_bookmark cond) {
		// TODO Auto-generated method stub
		return GlobalMapper.MemberBookmark.getList(cond);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public t_member_bookmark getData(t_member_bookmark cond) {
		// TODO Auto-generated method stub
		List<t_member_bookmark> list = GlobalMapper.MemberBookmark.getList(cond);
		if(list.size() == 0) return list.get(0);
		else return null;
	}
	@Transactional
	@Override
	public ResultData saveList(List<t_member_bookmark> list) {
		// TODO Auto-generated method stub
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			for(t_member_bookmark data : list) {
				GlobalMapper.MemberBookmark.delete(data);
				GlobalMapper.MemberBookmark.save(data);
			}
		}catch(Exception ex)
		{
			rtn.setEnResultType(enResultType.Error);
			rtn.setMessage(ex.getMessage());
		}
		return rtn;
	}
	@Transactional
	@Override
	public ResultData save(t_member_bookmark data) {
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.MemberBookmark.delete(data);
			GlobalMapper.MemberBookmark.save(data);
		}catch(Exception ex)
		{
			rtn.setEnResultType(enResultType.Error);
			rtn.setMessage(ex.getMessage());
		}
		return rtn;
	}

	@Transactional
	@Override
	public ResultData delete(t_member_bookmark data) {
		// TODO Auto-generated method stub
		if(data.getBookmark_code() == null )
		{
			if(data.getMember_code()== null)return  ResultData.builder().enResultType(enResultType.Error).message("회원 정보가 없습니다.").build();;
			if(		data.getBookmark_type() == null ||
					(data.getSign_code() == null && data.getBanner_code() == null && data.getLocalbox_code() == null))
			{
				return  ResultData.builder().enResultType(enResultType.Error).message("등록정보가 없습니다.").build();
			}
		}
		ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
		try {
			GlobalMapper.MemberBookmark.delete(data);
		}catch(Exception ex)
		{
			rtn.setEnResultType(enResultType.Error);
			rtn.setMessage(ex.getMessage());
		}
		return rtn;
	}

}
