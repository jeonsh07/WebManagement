package com.mrc.db.dto.mongoDb;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Document(collection="log_adPlace")
@Data
public class log_adPlace  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	/**
	 * mongodb 순번
	 */
	private String id;
	/**
	 * 구분 순번 : System.currentTimeMillis()  1970년 1월 1일 오전 0시와의 차이의 long 값
	 */
	private Long idx;
	/**
	 * 구분 순번별 서브 순번
	 */
	private Integer idx_sub;
	private Integer member_code;
	private String member_name;
	private Double latitude;
	private Double longitude;
	private String address;
	private String road_address;
	private Integer radius;
	private Integer limit_radius;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date reg_date;
}
