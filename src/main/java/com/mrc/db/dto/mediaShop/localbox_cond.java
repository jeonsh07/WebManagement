package com.mrc.db.dto.mediaShop;

import java.io.Serializable;
import java.util.List;

import com.mrc.db.dto.mediaShop.localbox_cond;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kyh
 *
 * 2020.07.29
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class localbox_cond implements Serializable{
	private Integer localbox_code;
	private Integer banner_code;
	private Short banner_type;
	private List<Integer> banner_codes;
	private Integer member_code;
	private String title;
	private String localer_name;
	private String company_type;
	private String area_code;
	private Integer page_start_no;
	private Integer page_in_data_num;
}
