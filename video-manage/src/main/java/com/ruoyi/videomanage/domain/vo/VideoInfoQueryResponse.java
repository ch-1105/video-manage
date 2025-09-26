package com.ruoyi.videomanage.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 视频文件信息查询返回对象
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Data
public class VideoInfoQueryResponse {

    /**
     * 视频ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long videoId;

    /**
     * 所属媒体库ID
     */
    private Long libraryId;

    /**
     * 原始文件名 (带后缀)
     */
    private String fileName;

    /**
     * 文件当前绝对路径
     */
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
