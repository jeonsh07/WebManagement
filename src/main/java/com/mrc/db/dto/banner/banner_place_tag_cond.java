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
public class banner_place_tag_cond implements Serializable {
	private Double _lat;
	private Double _long;
	private Integer _radius_type;
}
