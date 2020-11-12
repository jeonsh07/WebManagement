package com.mrc.controller;

import com.mrc.db.dao.GlobalDao;
import com.mrc.db.dto.common.ResultData;
import com.mrc.db.dto.common.enResultType;
import com.mrc.db.dto.member.member_cond;
import com.mrc.db.dto.member.t_member;
import com.mrc.db.dto.member.t_member_withdraw;
import com.mrc.framework.Global;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jeon.sh@altsoft.kr on 2020-11-02
 * Discription :
 */

@Controller
public class MemberWithdrawController {
    /**
     * 회원탈퇴 정보 가져오기
     * @param cond
     * @return
     */
    @ResponseBody
    @PostMapping("/memberwithdraw/getlist")
    public List<t_member_withdraw> getList(@RequestBody t_member_withdraw cond) {
        return GlobalDao.MemberWithDrawDao.getList(cond);
    }

    /**
     * 회원탈퇴 정보 저장
     * @param data
     * @return
     */
    @ResponseBody
    @PostMapping("/memberwithdraw/save")
    public ResultData save(t_member_withdraw data)
    {
        ResultData rtn = chkSave(data);
        if(rtn.enResultType == enResultType.Error) return rtn;

        data.setInsert_code(Global.LoginInfo.getMember().getMember_code());



        return GlobalDao.MemberWithDrawDao.save(data);
    }

    /**
     * 회원탈퇴 정보 삭제
     * @param data
     * @return
     */
    @ResponseBody
    @PostMapping("/memberwithdraw/delete")
    public ResultData delete(t_member_withdraw data)
    {
        ResultData rtn = chkSave(data);
        if(rtn.enResultType == enResultType.Error) return rtn;
        return GlobalDao.MemberWithDrawDao.delete(data);
    }

    /**
     * 저장 전 체크
     * @param data
     * @return
     */
    private ResultData chkSave(t_member_withdraw data) {
        if (!Global.LoginInfo.getIsLogin()) {
            return ResultData.builder().enResultType(enResultType.Error).message("로그인 정보가 없습니다.").data("-1").build();
        }

        String pw = Global.SecurityInfo.encryptSHA256(data.getMember_pw());
        member_cond cond = member_cond.builder().member_code(Global.LoginInfo.getMember().getMember_code()).member_pw(pw).build();

        List<t_member> rtnMember = GlobalDao.MemberDao.getList(cond);

        if (rtnMember.size() == 0) {
            return ResultData.builder().enResultType(enResultType.Error).message("패스워드가 잘못 입력되었습니다.").data("-2").build();
        }
        return ResultData.builder().enResultType(enResultType.Info).build();
    }
}
