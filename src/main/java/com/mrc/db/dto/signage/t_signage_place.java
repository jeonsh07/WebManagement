package com.mrc.db.dto.signage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
@Getter
@Setter
@Builder
public class t_signage_place implements Serializable {
	private Integer place_code;
	
	/**
	 * 광고영역구분 (T_COMMON: P003 1:실제주소 2:광고영역)
	 */
	@Builder.Default
	private Integer place_type=1;
	private Integer sign_code;
	private String area_code;
	private String region;
	private String address;
	private String road_address;
	private String zip_code;
	private String building;
	private Double latitude;
	private Double longitude;
	private Integer radius;
	private Integer limit_radius;
	@Builder.Default
	private boolean hide = false;
	
	private Integer insert_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp insert_date;
	private Integer update_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp update_date;
}
