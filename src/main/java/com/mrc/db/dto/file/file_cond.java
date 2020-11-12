package com.mrc.db.dto.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class file_cond {
	private String table_name;
	private String table_key;
	private Integer file_seq;
}
