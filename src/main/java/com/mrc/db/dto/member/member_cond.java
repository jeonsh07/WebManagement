package com.mrc.db.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

 
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class member_cond implements Serializable {
	private Integer member_code;
	private String member_id;

	private String member_pw;
	private String member_pw_reg;
	private String member_;
	private String member_name;
	private Integer auth_type;
	private String token;
	private String birth_date;
	private Boolean hide;

	private String business_number;
	private Boolean remember_me;
	private Integer insert_code;
}
