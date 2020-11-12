package com.mrc.db.dto.address;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class location_dong implements Serializable {
	private String code;
	private Integer square_code;
	/**
	 * 경도
	 */
	private Double longitude;
	/**
	 * 위도
	 */
	private Double latitude;
	private String dis_sido_name;
	private String sido_name;
	private String sigungu_name;
	private String dong2_name;
	private String gage;
}
