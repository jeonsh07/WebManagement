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
 *
 * 2020.06.12
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_banner implements Serializable {
	private Integer row_num;
	private Integer banner_code;
	private Integer localbox_code;
	private Short banner_type;
	private Short device_type;
	private Integer device_type2;
	private Short frame_type;
	@Builder.Default
	private Boolean is_vertical = false;
	@Builder.Default
	private Boolean is_private = false;
	private Short play_type;
	private Integer member_code;
	private String thumb_image_url;
	private String banner_image_url;
	private String banner_movie_url;
	private String title;
	private String sub_title;
	private String content;
	private Short status;
	private String remark;
	private Short bottom_banner_type;
	private Short seq;
	@Builder.Default
	private Boolean hide = false;
	
	private int insert_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp insert_date;
	private int update_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp update_date;
}
