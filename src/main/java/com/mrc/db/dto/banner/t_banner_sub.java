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
public class t_banner_sub implements Serializable {
	private Integer sub_idx;
	private Integer banner_code;
	private Short seq;
	private Integer play_type;
	private String thumb_image_url;
	private String banner_image_url;
	private String banner_movie_url;
	private String content;
	private Integer search_cnt;
	private Integer bookmark_cnt;
	private int insert_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp insert_date;
}
