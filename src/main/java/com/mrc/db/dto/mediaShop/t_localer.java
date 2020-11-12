package com.mrc.db.dto.mediaShop;

import java.io.Serializable;
import java.util.List;

import com.mrc.db.dto.mediaShop.t_localer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kyh
 *
 * 2020.09.15
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_localer implements Serializable{
	private Integer row_num;
	private Integer localbox_code;
	private Integer member_code;
	private String member_id;
	private String profile_url;
	private String nickname;
	private String company_name;
	private String company_type;
	private String address;
}
