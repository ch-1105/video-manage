package com.ruoyi.videomanage.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 视频文件信息对象 video_info
 *
 * @author ruoyi
 * @date 2025-09-25
 */
public class VideoInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 视频ID */
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
    private Integer duration;

    /** 分辨率 (例如: 1920x1080) */
    private String resolution;

    /** 文件大小 (单位: Byte) */
    private Long fileSize;

    /** 视频格式 (例如: mp4, mkv) */
    private String formatName;

    /** 状态 (0=正常, 1=文件丢失) */
    private String status;

    public void setVideoId(Long videoId)
    {
        this.videoId = videoId;
    }

    public Long getVideoId()
    {
        return videoId;
    }
    public void setLibraryId(Long libraryId)
    {
        this.libraryId = libraryId;
    }

    public Long getLibraryId()
    {
        return libraryId;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }
    public void setThumbnailPath(String thumbnailPath)
    {
        this.thumbnailPath = thumbnailPath;
    }

    public String getThumbnailPath()
    {
        return thumbnailPath;
    }
    public void setFileHash(String fileHash)
    {
        this.fileHash = fileHash;
    }

    public String getFileHash()
    {
        return fileHash;
    }
    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }

    public Integer getDuration()
    {
        return duration;
    }
    public void setResolution(String resolution)
    {
        this.resolution = resolution;
    }

    public String getResolution()
    {
        return resolution;
    }
    public void setFileSize(Long fileSize)
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize()
    {
        return fileSize;
    }
    public void setFormatName(String formatName)
    {
        this.formatName = formatName;
    }

    public String getFormatName()
    {
        return formatName;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("videoId", getVideoId())
            .append("libraryId", getLibraryId())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("thumbnailPath", getThumbnailPath())
            .append("fileHash", getFileHash())
            .append("duration", getDuration())
            .append("resolution", getResolution())
            .append("fileSize", getFileSize())
            .append("formatName", getFormatName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
