package com.mrc.db.dto.keyword;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
public class keyword_cond implements Serializable {
	
	private String keyword_name;
	private Integer keyword_type;
	private Integer keyword_code;
	private Integer base_code;
	private Boolean is_synonym;
	private String chosung;
	private String jungsung;
	private String jongsung;
	private String keyword_units;
	@Default
	private String sort_column_1 = "search_cnt desc" ;
	@Default
	private String sort_column_2 = "keyword_name" ;
	private String sort_type;
	@Default
	private Integer limit = 1000;
}
