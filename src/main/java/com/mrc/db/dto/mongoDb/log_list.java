package com.mrc.db.dto.mongoDb;

import java.io.Serializable;

import java.util.List;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class log_list implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<t_log> list;
	private long rowCount;
}
