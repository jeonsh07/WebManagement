package com.mrc.db.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: kyh
 * @Date: 2020. 4. 27.
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_member_company implements Serializable {
	private Integer member_code;
	private String branch_name;
	private String company_name;
	private Integer business_type;
	private String business_number;
	private Short profit_type;
	/**
	 * 중복 체크시 사용되는 코드
	 */
	private Integer chk_member_code;
	private int update_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp update_date;
}
