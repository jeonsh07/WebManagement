package com.mrc.db.dto.banner;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kyh
 * @Date: 2020. 6. 15.
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_banner_place_square implements Serializable {
	private Integer square_code;
	private Integer place_code;
	private String area_code;
	private Integer banner_code;
	private Integer square_type;
	private String name;
	private Integer gage_rate;
	
	private int insert_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp insert_date;
}
