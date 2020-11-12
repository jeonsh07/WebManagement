package com.mrc.db.mapper;

import com.mrc.db.dto.member.t_member_withdraw;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by jeon.sh@altsoft.kr on 2020-11-02
 * Discription :
 */
@Mapper
public interface IMemberWithdrawMapper {
    List<t_member_withdraw> getList(t_member_withdraw data);
    void save(t_member_withdraw data);
    void delete(t_member_withdraw data);
}
