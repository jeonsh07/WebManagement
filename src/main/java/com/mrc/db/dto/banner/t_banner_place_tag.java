package com.mrc.db.dto.banner;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kyh
 * @Date: 2020. 5. 17.
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_banner_place_tag implements Serializable {
	private String area_code;
	private String name;
	private Integer gage_rate;   
}
