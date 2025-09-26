package com.ruoyi.videomanage.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 本地文件夹对象 t_media_library
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_media_library")
public class MediaLibrary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 媒体库ID */
    @TableId(type = IdType.AUTO)
    private Long libraryId;

    /** 媒体库显示名称 */
    private String libraryName;

    /** 媒体库的绝对物理路径 */
    private String libraryPath;

}
