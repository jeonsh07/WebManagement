package com.mrc.db.dto.address;

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
public class t_address_area implements Serializable{
	private Integer level;
	private String code;
	private String name;
	private Integer keyword_code;
	private String display_name;
	private Double latitude;
	private Double longitude;
	private Boolean hide;
}
