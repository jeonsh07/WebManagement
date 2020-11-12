package com.mrc.db.dto.menu;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_menu implements Serializable{
	private Integer menu_code;
	private Integer project_type;
	private String search_code;
	private String menu_name;
	private String full_menu_name;
	private Integer menu_auth;
	private String menu_auth_name;
	private Integer menu_level;
	private String menu_url;
	private String menu_icon;
	private String menu_icon_style;
	private String remark;
	private Boolean hide;
	private Integer insert_code;
	private String insert_name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp insert_date;
	private Integer update_code;
	private String update_name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp update_date;
}
