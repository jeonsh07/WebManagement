package com.mrc.db.dto.user;

import java.io.Serializable;
import java.util.List;

import com.mrc.db.dto.banner.t_banner_sub;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class userBannerDetail implements Serializable{
	private Integer banner_code;
	private Integer banner_type;
	private String title;
	private String sub_title;
	private String content;
	private Integer play_type;
	private	String banner_image_url;
	private String banner_movie_url;
	private Integer member_code;
	private String member_name;
	private Integer bookmark_code;
	private Boolean is_bookmark;
	private Integer diff_min;
	private Integer bookmark_cnt;
	private Integer search_cnt;
	private String localbox_name;
	private Double latitude;
	private Double longitude;
	private String address;
	private String keywords;
	private String profile_url;
	private String area_code;
	private String area_name;
	private List<t_banner_sub> subList;
}
