package com.mrc.db.dto.mongoDb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class log_adPlace_cond implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _id;
	@Default
	private Integer page=1;
	@Default
	private Integer page_size=50;
	private Integer member_code;
	@Default
	private String sort="idx";
	
}
