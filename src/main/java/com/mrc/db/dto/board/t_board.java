package com.mrc.db.dto.board;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_board  implements Serializable {
	private Integer board_code;
	@Builder.Default
	private Integer board_type=1;
	private String title;
	private String content;
	@Builder.Default
	private Integer search_cnt=0;
	@Builder.Default
	private Boolean is_read = false;
	@Builder.Default
	private Boolean hide=false;
	private Integer insert_code;
	private String insert_name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime insert_date;
}
