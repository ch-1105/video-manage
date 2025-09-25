package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TagInfoMapper;
import com.ruoyi.system.domain.TagInfo;
import com.ruoyi.system.service.ITagInfoService;

/**
 * 标签信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
@Service
public class TagInfoServiceImpl implements ITagInfoService 
{
    @Autowired
    private TagInfoMapper tagInfoMapper;

    /**
     * 查询标签信息
     * 
     * @param tagId 标签信息主键
     * @return 标签信息
     */
    @Override
    public TagInfo selectTagInfoByTagId(Long tagId)
    {
        return tagInfoMapper.selectTagInfoByTagId(tagId);
    }

    /**
     * 查询标签信息列表
     * 
     * @param tagInfo 标签信息
     * @return 标签信息
     */
    @Override
    public List<TagInfo> selectTagInfoList(TagInfo tagInfo)
    {
        return tagInfoMapper.selectTagInfoList(tagInfo);
    }

    /**
     * 新增标签信息
     * 
     * @param tagInfo 标签信息
     * @return 结果
     */
    @Override
    public int insertTagInfo(TagInfo tagInfo)
    {
        tagInfo.setCreateTime(DateUtils.getNowDate());
        return tagInfoMapper.insertTagInfo(tagInfo);
    }

    /**
     * 修改标签信息
     * 
     * @param tagInfo 标签信息
     * @return 结果
     */
    @Override
    public int updateTagInfo(TagInfo tagInfo)
    {
        tagInfo.setUpdateTime(DateUtils.getNowDate());
        return tagInfoMapper.updateTagInfo(tagInfo);
    }

    /**
     * 批量删除标签信息
     * 
     * @param tagIds 需要删除的标签信息主键
     * @return 结果
     */
    @Override
    public int deleteTagInfoByTagIds(Long[] tagIds)
    {
        return tagInfoMapper.deleteTagInfoByTagIds(tagIds);
    }

    /**
     * 删除标签信息信息
     * 
     * @param tagId 标签信息主键
     * @return 结果
     */
    @Override
    public int deleteTagInfoByTagId(Long tagId)
    {
        return tagInfoMapper.deleteTagInfoByTagId(tagId);
    }
}
