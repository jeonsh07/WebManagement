package com.mrc.db.dto.mediaShop;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author kyh
 *
 * 2020.09.23
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_localbox_banner_request implements Serializable {
	private Integer request_code;
	private Integer banner_code;
	@Builder.Default
	private String request_desc = "";
	@Builder.Default
	private Boolean is_mobile = false;
	@Builder.Default
	private Boolean is_share = false;
	@Builder.Default
	private Boolean is_local = false;
	private Integer request_member_code;
	private Integer approval_member_code;
	private Integer fr_date;
	private Integer to_date;
	@Builder.Default
	private Short status = 1;
	private Short reject_type;
	private String reject_desc;
	private String remark;
	private List<Integer> localers;
	
	private int insert_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp insert_date;
	private int update_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp update_date;
}
