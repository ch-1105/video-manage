package com.ruoyi.videomanage.service;

import com.ruoyi.videomanage.domain.VideoInfo;
import java.util.List;

/**
 * 视频文件信息Service接口
 *
 * @author ruoyi
 * @date 2025-09-25
 */
public interface IVideoInfoService
{
    /**
     * 根据媒体库ID和路径扫描视频文件并入库
     *
     * @param libraryId 媒体库ID
     * @param path 媒体库物理路径
     * @return 本次新入库的视频数量
     */
    public int scanVideosAndSaveToDb(Long libraryId, String path);

    /**
     * 查询视频文件信息列表
     *
     * @param videoInfo 视频文件信息
     * @return 视频文件信息集合
     */
    public List<VideoInfo> selectVideoInfoList(VideoInfo videoInfo);
}
