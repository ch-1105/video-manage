package com.ruoyi.videomanage.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 视频与标签关联查询返回对象
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Data
public class VideoTagRelationQueryResponse {

    /**
     * 视频ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long videoId;

    /**
     * 标签ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;

}
