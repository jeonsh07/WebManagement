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
public class address_cond implements Serializable {
	private Double latitude;
	private Double longitude;
	private String sido_name;
	private String sigungu_name;
	private String dong2_name;
	private String address;
}
   