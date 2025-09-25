package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.VideoInfoMapper;
import com.ruoyi.system.domain.VideoInfo;
import com.ruoyi.system.service.IVideoInfoService;

/**
 * 视频文件信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
@Service
public class VideoInfoServiceImpl implements IVideoInfoService 
{
    @Autowired
    private VideoInfoMapper videoInfoMapper;

    /**
     * 查询视频文件信息
     * 
     * @param videoId 视频文件信息主键
     * @return 视频文件信息
     */
    @Override
    public VideoInfo selectVideoInfoByVideoId(Long videoId)
    {
        return videoInfoMapper.selectVideoInfoByVideoId(videoId);
    }

    /**
     * 查询视频文件信息列表
     * 
     * @param videoInfo 视频文件信息
     * @return 视频文件信息
     */
    @Override
    public List<VideoInfo> selectVideoInfoList(VideoInfo videoInfo)
    {
        return videoInfoMapper.selectVideoInfoList(videoInfo);
    }

    /**
     * 新增视频文件信息
     * 
     * @param videoInfo 视频文件信息
     * @return 结果
     */
    @Override
    public int insertVideoInfo(VideoInfo videoInfo)
    {
        videoInfo.setCreateTime(DateUtils.getNowDate());
        return videoInfoMapper.insertVideoInfo(videoInfo);
    }

    /**
     * 修改视频文件信息
     * 
     * @param videoInfo 视频文件信息
     * @return 结果
     */
    @Override
    public int updateVideoInfo(VideoInfo videoInfo)
    {
        videoInfo.setUpdateTime(DateUtils.getNowDate());
        return videoInfoMapper.updateVideoInfo(videoInfo);
    }

    /**
     * 批量删除视频文件信息
     * 
     * @param videoIds 需要删除的视频文件信息主键
     * @return 结果
     */
    @Override
    public int deleteVideoInfoByVideoIds(Long[] videoIds)
    {
        return videoInfoMapper.deleteVideoInfoByVideoIds(videoIds);
    }

    /**
     * 删除视频文件信息信息
     * 
     * @param videoId 视频文件信息主键
     * @return 结果
     */
    @Override
    public int deleteVideoInfoByVideoId(Long videoId)
    {
        return videoInfoMapper.deleteVideoInfoByVideoId(videoId);
    }
}
