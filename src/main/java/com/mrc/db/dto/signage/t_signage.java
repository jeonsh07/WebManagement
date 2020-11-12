package com.mrc.db.dto.signage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings("serial")
@Getter
@Setter
@Builder
public class t_signage implements Serializable {
	private Integer sign_code;
	private String sign_name;
	/**
	 * 사이니지유형(t_common : S002 1:내부배너 2:외부배너)
	 */
	private Integer sign_type;
	private String sign_type_name;
	/**
	 * 화면분할(D002 1:1Frame 6:6Frame)
	 */
	private Integer frame_type;
	private String frame_type_name;
	/**
	 * 세로설치여부(s004 : 1:세로 0:가로)
	 */
	private Boolean is_vertical;
	private String is_vertical_name;
	private Integer member_code;
	private String remark;
	@Builder.Default
	private boolean hide = false;
	private Integer insert_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp insert_date;
	private Integer update_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp update_date;
	private String insert_name;
	private String update_name;
	
	List<t_signage_place> placeList;
}
