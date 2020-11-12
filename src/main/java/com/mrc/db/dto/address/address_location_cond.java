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
public class address_location_cond implements Serializable {
	private Integer location_code;
	private Double search_latitude;
	private Double search_longitude;
	private Integer page;
	private Integer page_size ;
	private Integer startRow;
	private Integer startCode;
	private Boolean isXnull;
}
