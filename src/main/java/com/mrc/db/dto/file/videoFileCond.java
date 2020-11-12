package com.mrc.db.dto.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class videoFileCond {
	private String filePath;
	private double second;
	private Integer size;
}
