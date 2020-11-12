package com.mrc.db.dto.member;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 나의 공간 > 개인정보
 */
@Data
@Builder
public class memberInfo implements Serializable {
    private Integer member_code;
    private String address;
    private String address_detail;
    private String road_address;
    private String zip_code;
    private String business_number;
    private String business_type;
    private String phone;
    private Integer reg_code;
}
