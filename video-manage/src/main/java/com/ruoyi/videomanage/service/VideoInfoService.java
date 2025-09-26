package com.ruoyi.videomanage.service;

import com.ruoyi.videomanage.domain.VideoInfo;
import com.ruoyi.videomanage.domain.dto.VideoInfoSaveRequest;
import com.ruoyi.videomanage.domain.dto.VideoInfoQueryRequest;
import com.ruoyi.videomanage.domain.vo.VideoInfoQueryResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.page.PageResponse;

/**
 * 视频文件信息Service接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface VideoInfoService extends IService<VideoInfo>
{
    /**
     * 保存视频文件信息
     * 
     * @param request 视频文件信息保存请求
     */
    void save(VideoInfoSaveRequest request);

    /**
     * 查询视频文件信息列表
     * 
     * @param request 视频文件信息查询请求
     * @return 视频文件信息集合
     */
    PageResponse<VideoInfoQueryResponse> queryList(VideoInfoQueryRequest request);

    /**
     * 删除视频文件信息
     * 
     * @param videoId 视频文件信息主键
     */
    void delete(Long videoId);
}
