package com.mrc.db.dto.signage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@SuppressWarnings("serial")
@Getter
@Setter
@Builder
public class signage_cond implements Serializable {
	private Integer sign_code;
	private Integer place_code;
	private Integer member_code;
	private String sign_name;
	private String member_name;
	private Integer sign_type;
	private Integer frame_type;
	private Boolean hide;
}
