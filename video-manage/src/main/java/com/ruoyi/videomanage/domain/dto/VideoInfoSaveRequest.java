package com.ruoyi.videomanage.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 视频文件信息保存请求对象
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Data
public class VideoInfoSaveRequest {

    /**
     * 视频ID
     */
    private Long videoId;

    /**
     * 所属媒体库ID
     */
    @NotNull(message = "【所属媒体库ID】不能为空")
    private Long libraryId;

    /**
     * 原始文件名 (带后缀)
     */
    @NotBlank(message = "【原始文件名 (带后缀)】不能为空")
    private String fileName;

    /**
     * 文件当前绝对路径
     */
    @NotBlank(message = "【文件当前绝对路径】不能为空")
    private String filePath;

    /**
     * 缩略图存储路径
     */
    private String thumbnailPath;

    /**
     * 文件内容哈希值 (用于查重)
     */
    private String fileHash;

    /**
     * 视频时长 (单位: 秒)
     */
    private Long duration;

    /**
     * 分辨率 (例如: 1920x1080)
     */
    private String resolution;

    /**
     * 文件大小 (单位: Byte)
     */
    private Long fileSize;

    /**
     * 视频格式 (例如: mp4, mkv)
     */
    private String formatName;

    /**
     * 状态 (0=正常, 1=文件丢失)
     */
    private String status;

}
