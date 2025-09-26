package com.ruoyi.videomanage.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 视频与标签关联对象 t_video_tag_relation
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_video_tag_relation")
public class VideoTagRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 视频ID */
    @TableId(type = IdType.AUTO)
    private Long videoId;

    /** 标签ID */
    @TableId(type = IdType.AUTO)
    private Long tagId;

}
