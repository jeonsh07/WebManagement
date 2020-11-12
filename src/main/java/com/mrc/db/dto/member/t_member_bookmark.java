package com.mrc.db.dto.member;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_member_bookmark implements Serializable {
	private Integer bookmark_code;
	private String bookmark_name;
	private Integer bookmark_type;
	private Integer member_code;
	private Integer banner_code;
	private Integer localbox_code;
	private Integer sign_code;
	private String remark;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp insert_date;
	private Boolean is_bookmark;
}
