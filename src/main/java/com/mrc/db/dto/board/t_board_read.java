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
public class t_board_read implements Serializable {
	public Integer board_type;
	private int insert_code;
	private String board_codes;
	private String board_code;
}
