package com.ruoyi.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 本地文件夹对象 media_library
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MediaLibrary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 媒体库ID */
    private Long libraryId;

    /** 媒体库显示名称 */
    @Excel(name = "媒体库显示名称")
    private String libraryName;

    /** 媒体库的绝对物理路径 */
    @Excel(name = "媒体库的绝对物理路径")
    private String libraryPath;

    public void setLibraryId(Long libraryId) 
    {
        this.libraryId = libraryId;
    }

    public Long getLibraryId() 
    {
        return libraryId;
    }

    public void setLibraryName(String libraryName) 
    {
        this.libraryName = libraryName;
    }

    public String getLibraryName() 
    {
        return libraryName;
    }

    public void setLibraryPath(String libraryPath) 
    {
        this.libraryPath = libraryPath;
    }

    public String getLibraryPath() 
    {
        return libraryPath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("libraryId", getLibraryId())
            .append("libraryName", getLibraryName())
            .append("libraryPath", getLibraryPath())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
