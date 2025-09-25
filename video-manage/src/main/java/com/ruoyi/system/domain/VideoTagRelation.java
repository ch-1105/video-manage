package com.ruoyi.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 视频与标签关联对象 video_tag_relation
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VideoTagRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 视频ID */
    private Long videoId;

    /** 标签ID */
    private Long tagId;

    public void setVideoId(Long videoId) 
    {
        this.videoId = videoId;
    }

    public Long getVideoId() 
    {
        return videoId;
    }

    public void setTagId(Long tagId) 
    {
        this.tagId = tagId;
    }

    public Long getTagId() 
    {
        return tagId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("videoId", getVideoId())
            .append("tagId", getTagId())
            .toString();
    }
}
