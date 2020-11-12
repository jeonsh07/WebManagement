package com.mrc.db.dto.address;

import java.io.Serializable;
import java.math.BigInteger;
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
public class t_address_location1 implements Serializable{
	private Integer idx;
	private String code;
	private Double search_latitude;
	private Double search_longitude;
	private String dong_code;
	private Double latitude;
	private Double longitude;
	private String sido_name;
	private String sigungu_name;
	private String dong2_name;
	private Double gage_rate;
	private BigInteger search_cnt; 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp insert_date;
}
