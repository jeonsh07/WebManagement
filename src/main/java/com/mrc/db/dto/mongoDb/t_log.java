package com.mrc.db.dto.mongoDb;
import java.util.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


@Document(collection="log")
@Data
public class t_log {
	@Id
	private String id;
	
	private Integer member_code;
	private String member_name;
	private String device_type;/*pc, mobile, signage*/
	private String page_name;
	private String page_url;
	private String params;
	private Double latitude;
	private Double longitude;
	private Integer banner_code;
	private String title;
	private Integer sign_code;
	private String sign_name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date reg_date;
}
