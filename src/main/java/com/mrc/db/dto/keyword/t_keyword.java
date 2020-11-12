package com.mrc.db.dto.keyword;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.poi.hpsf.Decimal;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Author: kyh
 * @Date: 2020. 4. 2.
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_keyword implements Serializable {
	private Integer keyword_code;
	private Short keyword_type;
	private String keyword_name;
	private String keyword_desc;
	private Integer base_code;
	@Builder.Default
	private Boolean is_synonym= false;
	private String chosung;
	private String jungsung;
	private String jongsung;
	private String keyword_units;
	private Decimal latitude;
	private Decimal longitude;
	@Builder.Default
	private Short status = 1;
	@Builder.Default
	private Boolean hide = false;
	@Builder.Default
	private Integer search_cnt=0;
	private int insert_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp insert_date;
	private int update_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp update_date;
	
}
