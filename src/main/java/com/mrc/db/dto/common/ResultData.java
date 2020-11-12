package com.mrc.db.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultData {
	public enResultType enResultType ;
	public String data;
	public String data2;
	public String message ;
}
