package com.mrc.db.dto.banner;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author kyh
 *
 * 2020.09.23
 */

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class t_banner_request implements Serializable {
    private Integer row_num;
    private Integer request_code;
    private Integer banner_code;
    @Builder.Default
    private String request_desc = "";
    @Builder.Default
    private Boolean is_mobile = false;
    @Builder.Default
    private Boolean is_share = false;
    @Builder.Default
    private Boolean is_local = false;
    private Integer request_member_code;
    private String approval_member_code;
    private String fr_date;
    private Timestamp req_date;
    @Builder.Default
    private Short req_status = 1;
    private Short reject_type;
    private String reject_desc;
    private String remark;
    private List<String> localers;
    private Short banner_type;
    private Short device_type;
    private Integer device_type2;
    private Short frame_type;
    @Builder.Default
    private Boolean is_vertical = false;
    @Builder.Default
    private Boolean is_private = false;
    private Short play_type;
    private Integer member_code;
    private String thumb_image_url;
    private String banner_image_url;
    private String banner_movie_url;
    private String title;
    private String sub_title;
    private String content;

    private int insert_code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp insert_date;
    private int update_code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp update_date;
}
