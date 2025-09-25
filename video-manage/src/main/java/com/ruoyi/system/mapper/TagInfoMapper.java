package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TagInfo;

/**
 * 标签信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
public interface TagInfoMapper 
{
    /**
     * 查询标签信息
     * 
     * @param tagId 标签信息主键
     * @return 标签信息
     */
    public TagInfo selectTagInfoByTagId(Long tagId);

    /**
     * 查询标签信息列表
     * 
     * @param tagInfo 标签信息
     * @return 标签信息集合
     */
    public List<TagInfo> selectTagInfoList(TagInfo tagInfo);

    /**
     * 新增标签信息
     * 
     * @param tagInfo 标签信息
     * @return 结果
     */
    public int insertTagInfo(TagInfo tagInfo);

    /**
     * 修改标签信息
     * 
     * @param tagInfo 标签信息
     * @return 结果
     */
    public int updateTagInfo(TagInfo tagInfo);

    /**
     * 删除标签信息
     * 
     * @param tagId 标签信息主键
     * @return 结果
     */
    public int deleteTagInfoByTagId(Long tagId);

    /**
     * 批量删除标签信息
     * 
     * @param tagIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTagInfoByTagIds(Long[] tagIds);
}
