package com.ruoyi.videomanage.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 本地文件夹查询返回对象
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Data
public class MediaLibraryQueryResponse {

    /**
     * 媒体库ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long libraryId;

    /**
     * 媒体库显示名称
     */
    private String libraryName;

    /**
     * 媒体库的绝对物理路径
     */
    private String libraryPath;

}
