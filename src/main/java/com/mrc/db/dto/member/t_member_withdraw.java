package com.mrc.db.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jeon.sh@altsoft.kr on 2020-11-02
 * Discription : 회원 탈퇴
 */
@Data
@Builder
public class t_member_withdraw implements Serializable {
    private Integer member_code;
    private String member_pw;
    private String remark;
    private Boolean is_reasion1;
    private Boolean is_reasion2;
    private Boolean is_reasion3;
    private Boolean is_reasion4;
    private int insert_code;

    private Timestamp insert_date;
    @Builder.Default
    private Integer waiting_day = 0;
    private Integer withdraw_code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp withdraw_date;
}
