package com.mrc.db.dto.mediaShop;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kyh
 *
 * 2020.07.29
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_localbox_place implements Serializable {
	private Integer place_code;
	private Integer localbox_code;
	private String area_code;
	private String region;
	private String address;
	private String road_address;
	private String address_detail;
	private String zip_code;
	private Double latitude;
	private Double longitude;
	private Integer radius;
	@Builder.Default
	private Boolean hide = false;
	
	private int insert_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp insert_date;
	private int update_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp update_date;
}
