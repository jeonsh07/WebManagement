package com.mrc.db.dao.impl;

import com.mrc.db.dto.common.ResultData;

/**
 * Created by jeon.sh@altsoft.kr on 2020-11-02
 * Discription :
 */
public interface IMemberWithDrawDao<t_member_withdraw>  extends IBaseDao<t_member_withdraw, t_member_withdraw> {
    ResultData delete (t_member_withdraw data);
}
