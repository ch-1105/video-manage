package com.ruoyi.videomanage.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 视频与标签关联保存请求对象
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Data
public class VideoTagRelationSaveRequest {

    /**
     * 视频ID
     */
    private Long videoId;

    /**
     * 标签ID
     */
    private Long tagId;

}
