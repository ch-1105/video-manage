package com.ruoyi.videomanage.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 视频文件信息对象 t_video_info
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_video_info")
public class VideoInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 视频ID */
    @TableId(type = IdType.AUTO)
    private Long videoId;

    /** 所属媒体库ID */
    private Long libraryId;

    /** 原始文件名 (带后缀) */
    private String fileName;

    /** 文件当前绝对路径 */
    private String filePath;

    /** 缩略图存储路径 */
    private String thumbnailPath;

    /** 文件内容哈希值 (用于查重) */
    private String fileHash;

    /** 视频时长 (单位: 秒) */
    private String duration;

    /** 分辨率 (例如: 1920x1080) */
    private String resolution;

    /** 文件大小 (单位: Byte) */
    private String fileSize;

    /** 视频格式 (例如: mp4, mkv) */
    private String formatName;

    /** 状态 (0=正常, 1=文件丢失) */
    private String status;

}
