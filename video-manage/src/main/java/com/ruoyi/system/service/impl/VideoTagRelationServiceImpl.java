package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.VideoTagRelationMapper;
import com.ruoyi.system.domain.VideoTagRelation;
import com.ruoyi.system.service.IVideoTagRelationService;

/**
 * 视频与标签关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
@Service
public class VideoTagRelationServiceImpl implements IVideoTagRelationService 
{
    @Autowired
    private VideoTagRelationMapper videoTagRelationMapper;

    /**
     * 查询视频与标签关联
     * 
     * @param videoId 视频与标签关联主键
     * @return 视频与标签关联
     */
    @Override
    public VideoTagRelation selectVideoTagRelationByVideoId(Long videoId)
    {
        return videoTagRelationMapper.selectVideoTagRelationByVideoId(videoId);
    }

    /**
     * 查询视频与标签关联列表
     * 
     * @param videoTagRelation 视频与标签关联
     * @return 视频与标签关联
     */
    @Override
    public List<VideoTagRelation> selectVideoTagRelationList(VideoTagRelation videoTagRelation)
    {
        return videoTagRelationMapper.selectVideoTagRelationList(videoTagRelation);
    }

    /**
     * 新增视频与标签关联
     * 
     * @param videoTagRelation 视频与标签关联
     * @return 结果
     */
    @Override
    public int insertVideoTagRelation(VideoTagRelation videoTagRelation)
    {
        return videoTagRelationMapper.insertVideoTagRelation(videoTagRelation);
    }

    /**
     * 修改视频与标签关联
     * 
     * @param videoTagRelation 视频与标签关联
     * @return 结果
     */
    @Override
    public int updateVideoTagRelation(VideoTagRelation videoTagRelation)
    {
        return videoTagRelationMapper.updateVideoTagRelation(videoTagRelation);
    }

    /**
     * 批量删除视频与标签关联
     * 
     * @param videoIds 需要删除的视频与标签关联主键
     * @return 结果
     */
    @Override
    public int deleteVideoTagRelationByVideoIds(Long[] videoIds)
    {
        return videoTagRelationMapper.deleteVideoTagRelationByVideoIds(videoIds);
    }

    /**
     * 删除视频与标签关联信息
     * 
     * @param videoId 视频与标签关联主键
     * @return 结果
     */
    @Override
    public int deleteVideoTagRelationByVideoId(Long videoId)
    {
        return videoTagRelationMapper.deleteVideoTagRelationByVideoId(videoId);
    }
}
