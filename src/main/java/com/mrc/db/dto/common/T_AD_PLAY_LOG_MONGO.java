package com.mrc.db.dto.common;


import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


/**
 * @author  : jsh
 * @Date    : 2020. 3. 24.
 * @Version : 
 */
@Document(collection="adPlayLog")
@Data
@Builder
@ToString
public class T_AD_PLAY_LOG_MONGO {
	private ObjectId _id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date REG_DATE;
    private String REG_DAY;
    /**
     * 디바이스종류(T_COMMON MAIN_CODE : L003) 1:모바일, 2:로컬박스
     */
    private Integer DEVICE_KIND;
    
    /// 디바이스종류
    private String DEVICE_KIND_NAME;
    
    /// 화면분할(T_COMMON => MAIN_CODE: H002 1:1Frame, 12:12Frame)
    private Integer FRAME_TYPE;
   
    /// 화면분할(T_COMMON => MAIN_CODE: H002 1:1Frame, 12:12Frame)
    private String FRAME_TYPE_NAME;

    /// 재생유형(T_COMON P004, 1:조회,2:클릭,3:재생)
    private Integer PLAY_TYPE;
    /// <summary>
    /// 재생유형(T_COMON P004, 1:조회,2:클릭,3:재생)
    /// </summary>
    private String PLAY_TYPE_NAME;
    /// 로컬박스코드(T_DEVICE)
    private Long DEVICE_CODE;

    /// 로컬박스명
    private String DEVICE_NAME;
       
    /**
     * 광고코드(T_AD) 
     */
    private Long AD_CODE;

    
    /**
     * 광고제목 
     */
    private String TITLE;

    /// <summary>       
    /// 플레이시간 NUMERIC(7,3) PLAY_TYPE 클릭(2) 일경우 60초로 등록
    /// </summary>	  
    
    private Long PLAY_TIME;
    /// <summary>       
    /// 비고
    /// </summary>	   
    private String REMARK;
    /// <summary>
    ///  T_AD : BANNER_TYPE2 >> 배너유형(1:이미지,2:동영상,3:유튜브-T_COMMON : A010)
    /// </summary>
    private Integer BANNER_TYPE2;
    /// <summary>
    ///  T_AD : BANNER_TYPE2 >> 배너유형(1:이미지,2:동영상,3:유튜브-T_COMMON : A010)
    /// </summary>
   
    
    private String BANNER_TYPE2_NAME;

    
    /// 배너구분(B008 1:내배너 2:일반배너
    private Integer BANNER_KIND;
    private String BANNER_KIND_NAME;
}
