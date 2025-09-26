package com.ruoyi.videomanage.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 本地文件夹保存请求对象
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Data
public class MediaLibrarySaveRequest {

    /**
     * 媒体库ID
     */
    private Long libraryId;

    /**
     * 媒体库显示名称
     */
    @NotBlank(message = "【媒体库显示名称】不能为空")
    private String libraryName;

    /**
     * 媒体库的绝对物理路径
     */
    @NotBlank(message = "【媒体库的绝对物理路径】不能为空")
    private String libraryPath;

}
