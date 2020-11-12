package com.mrc.db.dto.file;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class t_file implements Serializable {
	private Integer code;
	private String table_name;
	private String table_key;
	private Integer file_seq;
	private String file_name;
	private String file_ext;
	private Integer file_size;
	private String file_url;
	private String remark;
	private Integer insert_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp insert_date;
	
}
