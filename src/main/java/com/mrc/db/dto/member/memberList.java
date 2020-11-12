package com.mrc.db.dto.member;

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
public class memberList implements Serializable {
	private int member_code;
	private String member_id;
	private String member_name;
	private String auth_type_name;
	private String member_img;
	private Float member_imgsize;
	private String birth_date;
}
