package com.mrc.db.dto.mongoDb;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class log_cond implements Serializable {
	
	/**
	 * 
	 */
	private String searchText;
	private static final long serialVersionUID = 1L;
	@Default
	private Integer page=1;
	@Default
	private Integer page_size=10;
	private String _id;
	private Integer member_code;
	private String member_name;
	private String fr_date;
	private String to_date;
	
	private String device_type;/*pc, mobile, signage*/
	@Default
	private String sort="reg_date";
}
