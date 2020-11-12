package com.mrc.db.dto.member;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: kyh
 * @Date: 2020. 4. 22.
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_business_type implements Serializable {
	private Integer code;
	private String name;
}
