package com.mrc.db.dto.board;

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
public class board_cond implements Serializable {
	private Integer board_code;
	@Builder.Default
	private Integer board_type =1;
	private String title;
	private String content;
	private Integer insert_code;
	private Integer member_code;
	private Boolean is_read;
	@Builder.Default
	private Integer start_row = 0;
	@Builder.Default
	private Integer row_size = 5;
	/**
	 * 읽은 글 코드
	 */
	private String board_codes;
}
