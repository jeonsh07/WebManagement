package com.mrc.db.dto.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 주소 위치정보 
 * @author jsh
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_address_location {
	private Integer location_code;
	/**
	 * 읍면동코드
	 */
	private String dong_code;
	/**
	 * 시도명
	 */
	private String sido_name;
	/**
	 * 시군구명
	 */
	private String sigungu_name;
	/**
	 * 읍명동명
	 */
	private String dong_name;
	/**
	 * 우편번호
	 */
	private String zip_code;
	/**
	 * utm-k x좌표
	 */
	private Double x;
	/**
	 * utm-k y좌표
	 */
	private Double y;
	/**
	 * 검색용경도
	 */
	private Double search_longitude;
	/**
	 * 검색용위도
	 */
	private Double search_latitude;
	/**
	 * 경도
	 */
	private Double longitude;
	/**
	 * 위도
	 */
	private Double latitude;
	
}
