package com.mrc.db.dto.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class userLocalboxDetail implements Serializable{
	private Integer member_code;
	private String member_name;
	private Integer localbox_code;
	private String localbox_name;
	private String phone;
	private String profile_url;
	private String desc;
	private String announce;
	private Integer bookmark_code;
	private Boolean is_bookmark;
	private Integer bookmark_cnt;
	private String business_time;
	private Integer business_type;
	private String business_type_name;
	private Integer frame1_cnt;
	private Integer frame6_cnt;
	private String address;
	private String road_address;
	private String address_detail;
	@Default
	private String area_code="삼성동";
	@Default
	private String area_name="01|01|17";
	
}
