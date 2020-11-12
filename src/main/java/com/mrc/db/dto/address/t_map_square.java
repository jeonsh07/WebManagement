package com.mrc.db.dto.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_map_square {
	private Integer square_code;
	private Double search_latitude;
	private Double search_longitude;
	private Double latitude;
	private Double longitude;
}
