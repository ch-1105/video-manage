package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.VideoInfo;

/**
 * 视频文件信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
public interface VideoInfoMapper 
{
    /**
     * 查询视频文件信息
     * 
     * @param videoId 视频文件信息主键
     * @return 视频文件信息
     */
    public VideoInfo selectVideoInfoByVideoId(Long videoId);

    /**
     * 查询视频文件信息列表
     * 
     * @param videoInfo 视频文件信息
     * @return 视频文件信息集合
     */
    public List<VideoInfo> selectVideoInfoList(VideoInfo videoInfo);

    /**
     * 新增视频文件信息
     * 
     * @param videoInfo 视频文件信息
     * @return 结果
     */
    public int insertVideoInfo(VideoInfo videoInfo);

    /**
     * 修改视频文件信息
     * 
     * @param videoInfo 视频文件信息
     * @return 结果
     */
    public int updateVideoInfo(VideoInfo videoInfo);

    /**
     * 删除视频文件信息
     * 
     * @param videoId 视频文件信息主键
     * @return 结果
     */
    public int deleteVideoInfoByVideoId(Long videoId);

    /**
     * 批量删除视频文件信息
     * 
     * @param videoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVideoInfoByVideoIds(Long[] videoIds);
}
