package com.mrc.db.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: kyh
 * @Date: 2020. 4. 22.
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class business_type_cond implements Serializable {
	private Integer parent_code;
	private Integer level;
}
