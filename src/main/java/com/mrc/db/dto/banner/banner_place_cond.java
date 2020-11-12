package com.mrc.db.dto.banner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kyh
 * @Date: 2020. 5. 17.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class banner_place_cond {
	private Integer place_code;
	private Double latitude;
	private Double longitude;
	private Integer radius;
}
