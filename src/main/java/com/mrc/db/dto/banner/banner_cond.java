package com.mrc.db.dto.banner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author kyh
 *
 * 2020.06.12
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class banner_cond implements Serializable {
	private Integer banner_code;
	private Integer member_code;
	private String member_id;
	private String title;
	private List<Integer> device_type2;
	private List<Short> banner_type;
	private List<Short> service;
	private List<Short> statuses;
	@Builder.Default
	private Short status = 1;
	private String date_seq;
	private Integer page_start_no;
	private Integer page_in_data_num;
}
