package com.mrc.db.dto.user;

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
public class userBannerList implements Serializable{
	private Integer member_code;
	private String member_name;
	private Integer banner_code;
	private String title;
	private String sub_title;
	private Integer banner_type;
	private String content;
	private String thumb_image_url;
	private Integer search_cnt;
	private Integer diff_min;
	private String area_data;
	private Integer max_gate;
	private String localbox_name;
	private String profile_url;
	private Boolean is_bookmark;
	private Integer bookmark_code;
}
