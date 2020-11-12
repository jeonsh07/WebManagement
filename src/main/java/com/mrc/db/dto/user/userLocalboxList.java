package com.mrc.db.dto.user;

import java.io.Serializable;

import org.apache.poi.hpsf.Decimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class userLocalboxList implements Serializable{
	private Integer banner_code;
	private Integer member_code;
	private String member_name;
	private String name;
	private String desc;
	private String profile_url;
	private String address;
	private String area_address;
	private Integer localbox_code;
	private Decimal distance;
	private Boolean is_bookmark;
	private Integer bookmark_code;
}
