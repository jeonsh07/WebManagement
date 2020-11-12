package com.mrc.db.dao;

import com.mrc.db.dao.impl.IMemberWithDrawDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.dto.member.t_member_withdraw;
import com.mrc.db.mapper.GlobalMapper;
import com.mrc.framework.Global;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jeon.sh@altsoft.kr on 2020-11-02
 * Discription : 회원탈퇴 DAO
 */
@Repository
public class MemberWithdrawDao implements IMemberWithDrawDao<t_member_withdraw> {
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public List<t_member_withdraw> getList(t_member_withdraw cond) {
        return GlobalDao.MemberWithDrawDao.getList(cond);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public t_member_withdraw getData(t_member_withdraw cond) {
        List<t_member_withdraw> rtn = this.getList(cond);
        if(rtn.size() == 1) return rtn.get(0);
        else return null;
    }

    @Transactional
    @Override
    public ResultData saveList(List<t_member_withdraw> list) {

        ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
        try {
            for (t_member_withdraw data : list)
                GlobalMapper.MemberWithdrawMapper.save(data);
        }catch(Exception ex)
        {
            rtn.setEnResultType(enResultType.Error);
            rtn.setMessage(ex.getMessage());
        }
        return rtn;
    }

    @Transactional
    @Override
    public ResultData save(t_member_withdraw data) {
        ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
        try {
            if(data.getMember_code() == null) data.setMember_code(data.getInsert_code());
            if(data.getWithdraw_code() == null) data.setWithdraw_code(data.getInsert_code());

            if(data.getWaiting_day() == null) data.setWaiting_day(0);

            data.setInsert_date(Global.DataInfo.nowTimestamp());

            data.setWithdraw_date(Global.DataInfo.addHoursToTimestamp(data.getInsert_date(), 24 * data.getWaiting_day()));

            GlobalMapper.MemberWithdrawMapper.save(data);

        }catch(Exception ex)
        {
            rtn.setEnResultType(enResultType.Error);
            rtn.setMessage(ex.getMessage());
        }
        return rtn;
    }

    @Override
    public ResultData delete(t_member_withdraw data) {
        ResultData rtn = ResultData.builder().enResultType(enResultType.Info).build();
        try {
            GlobalMapper.MemberWithdrawMapper.delete(data);
        }catch(Exception ex)
        {
            rtn.setEnResultType(enResultType.Error);
            rtn.setMessage(ex.getMessage());
        }
        return rtn;
    }
}
