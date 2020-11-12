package com.mrc.db.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class userSearchCond implements Serializable{
	private Integer search_code;
	
	private Double latitude;
	private Double longitude;
	private String area_code;
	private String area_name;
	private Integer keyword_code;
	private String keyword_name;
	private String currentArea;
	@Default
	private Integer member_code = -1;
	
	private Integer diff_min;
	private Integer search_cnt;
	@Default
	private Integer add_search_cnt = 1;

	private String orderBy ;
	private Boolean is_bookmark;
}
