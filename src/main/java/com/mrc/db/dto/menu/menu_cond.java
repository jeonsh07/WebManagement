package com.mrc.db.dto.menu;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class menu_cond implements Serializable {
	private Integer menu_code;
	private Integer project_type;
	private String search_code;
	private Integer menu_auth;
	private String menu_name;
	private Boolean hide;
}
