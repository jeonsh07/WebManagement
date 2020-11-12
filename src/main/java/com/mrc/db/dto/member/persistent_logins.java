package com.mrc.db.dto.member;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class persistent_logins implements Serializable {
	private String series;
	private String user_name;
	private String token;
	private Timestamp last_used;
	private String saveType;
}
