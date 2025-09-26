package com.ruoyi.videomanage.service;

import com.ruoyi.videomanage.domain.VideoTagRelation;
import com.ruoyi.videomanage.domain.dto.VideoTagRelationSaveRequest;
import com.ruoyi.videomanage.domain.dto.VideoTagRelationQueryRequest;
import com.ruoyi.videomanage.domain.vo.VideoTagRelationQueryResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.page.PageResponse;

/**
 * 视频与标签关联Service接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface VideoTagRelationService extends IService<VideoTagRelation>
{
    /**
     * 保存视频与标签关联
     * 
     * @param request 视频与标签关联保存请求
     */
    void save(VideoTagRelationSaveRequest request);

    /**
     * 查询视频与标签关联列表
     * 
     * @param request 视频与标签关联查询请求
     * @return 视频与标签关联集合
     */
    PageResponse<VideoTagRelationQueryResponse> queryList(VideoTagRelationQueryRequest request);

    /**
     * 删除视频与标签关联
     * 
     * @param videoId 视频与标签关联主键
     */
    void delete(Long videoId);
}
