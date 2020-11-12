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
public class map_square_cond implements Serializable {
	private Integer square_code;
	private Double search_latitude;
	private Double search_longitude;
}
