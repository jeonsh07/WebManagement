package com.mrc.db.dto.mongoDb;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class log_keyword {
	Integer keyword_code;
	String keyword_name;
	private Double latitude;
	private Double longitude;
	private Integer member_code;
	private String member_name;
	/*t_map_square 테이블의 square_code */
	private Integer square_code;
	private String area_code;
	private String area_name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@Builder.Default
	private Date reg_date = new Date();
	
}
