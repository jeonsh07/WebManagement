/**
 * 
 */
package com.mrc.db.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author jsh
 *
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_member implements Serializable {
	private Integer member_code;
	private String member_id;
	private String member_name;
	private String nickname;
	@JsonIgnore
	private String member_pw;
	private String member_pw_reg;
	@Builder.Default
	private Integer auth_type = 30;
	private String auth_type_name;
	private String birth_date;
	private String phone;
	private Boolean service; 
	private String token;
	@Builder.Default
	private Boolean hide = false;
	
	private String branch_name;
	private String company_name;
	private Integer business_type;
	private String business_type_name;
	private String business_number;
	private Short profit_type;
	private String area_name;
	
	private Integer localbox_code;
	private String zip_code;
	private String address;
	private String address_detail;
	private String profile_url;
	
	private int insert_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp insert_date;
	private int update_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp update_date;
	private int company_update_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp company_update_date;

}
