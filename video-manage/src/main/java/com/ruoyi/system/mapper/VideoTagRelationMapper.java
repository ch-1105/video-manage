package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.VideoTagRelation;

/**
 * 视频与标签关联Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
public interface VideoTagRelationMapper 
{
    /**
     * 查询视频与标签关联
     * 
     * @param videoId 视频与标签关联主键
     * @return 视频与标签关联
     */
    public VideoTagRelation selectVideoTagRelationByVideoId(Long videoId);

    /**
     * 查询视频与标签关联列表
     * 
     * @param videoTagRelation 视频与标签关联
     * @return 视频与标签关联集合
     */
    public List<VideoTagRelation> selectVideoTagRelationList(VideoTagRelation videoTagRelation);

    /**
     * 新增视频与标签关联
     * 
     * @param videoTagRelation 视频与标签关联
     * @return 结果
     */
    public int insertVideoTagRelation(VideoTagRelation videoTagRelation);

    /**
     * 修改视频与标签关联
     * 
     * @param videoTagRelation 视频与标签关联
     * @return 结果
     */
    public int updateVideoTagRelation(VideoTagRelation videoTagRelation);

    /**
     * 删除视频与标签关联
     * 
     * @param videoId 视频与标签关联主键
     * @return 结果
     */
    public int deleteVideoTagRelationByVideoId(Long videoId);

    /**
     * 批量删除视频与标签关联
     * 
     * @param videoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVideoTagRelationByVideoIds(Long[] videoIds);
}
