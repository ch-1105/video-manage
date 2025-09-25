package com.ruoyi.videomanage.mapper;

import java.util.List;
import com.ruoyi.videomanage.domain.VideoInfo;

/**
 * 视频文件信息Mapper接口
 *
 * @author ruoyi
 * @date 2025-09-25
 */
public interface VideoInfoMapper
{

    /**
     * 批量新增视频文件信息
     *
     * @param videoInfoList 视频文件信息列表
     * @return 结果
     */
    public int batchInsertVideoInfo(List<VideoInfo> videoInfoList);

    /**
     * 根据媒体库ID查询已存在的文件路径列表
     *
     * @param libraryId 媒体库ID
     * @return 文件路径列表
     */
    public List<String> selectFilePathsByLibraryId(Long libraryId);

    /**
     * 查询视频文件信息列表
     *
     * @param videoInfo 视频文件信息
     * @return 视频文件信息集合
     */
    public List<VideoInfo> selectVideoInfoList(VideoInfo videoInfo);

}
