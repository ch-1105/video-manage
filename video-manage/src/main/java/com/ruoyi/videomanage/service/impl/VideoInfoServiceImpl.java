package com.ruoyi.videomanage.service.impl;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.videomanage.domain.MediaLibrary;
import com.ruoyi.videomanage.utils.FFmpegUtils;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.page.PageResponse;

import com.ruoyi.videomanage.mapper.VideoInfoMapper;
import com.ruoyi.videomanage.domain.VideoInfo;
import com.ruoyi.videomanage.domain.dto.VideoInfoSaveRequest;
import com.ruoyi.videomanage.domain.dto.VideoInfoQueryRequest;
import com.ruoyi.videomanage.domain.vo.VideoInfoQueryResponse;
import com.ruoyi.videomanage.service.VideoInfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 视频文件信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class VideoInfoServiceImpl extends ServiceImpl<VideoInfoMapper, VideoInfo> implements VideoInfoService
{
    private static final Logger log = LoggerFactory.getLogger(VideoInfoService.class);

    @Resource
    private VideoInfoMapper videoInfoMapper;


    @Autowired
    private FFmpegUtils ffmpegUtils; // 你需要自己封装的工具类


    @Override
    public void save(VideoInfoSaveRequest request) {
        DateTime now = DateTime.now();
        VideoInfo videoInfo = BeanUtil.copyProperties(request, VideoInfo.class);
        if (ObjectUtil.isNull(videoInfo.getVideoId())) {
            videoInfo.setVideoId(IdUtil.getSnowflakeNextId());
            videoInfo.setCreateTime(now);
            videoInfo.setUpdateTime(now);
            baseMapper.insert(videoInfo);
        } else {
            videoInfo.setUpdateTime(now);
            baseMapper.updateById(videoInfo);
        }
    }

    @Override
    public PageResponse<VideoInfoQueryResponse> queryList(VideoInfoQueryRequest request) {
        QueryWrapper<VideoInfo> videoInfoWrapper = new QueryWrapper<>();
        videoInfoWrapper.orderByDesc("video_id");

        log.info("查询页码：{}", request.getPageNum());
        log.info("每页条数：{}", request.getPageSize());
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<VideoInfo> videoInfoList = baseMapper.selectList(videoInfoWrapper);

        PageInfo<VideoInfo> pageInfo = new PageInfo<>(videoInfoList);
        log.info("总行数：{}", pageInfo.getTotal());
        log.info("总页数：{}", pageInfo.getPages());

        List<VideoInfoQueryResponse> list = BeanUtil.copyToList(videoInfoList, VideoInfoQueryResponse.class);

        PageResponse<VideoInfoQueryResponse> pageResponse = new PageResponse<>();
        pageResponse.setTotal(pageInfo.getTotal());
        pageResponse.setList(list);
        return pageResponse;
    }

    @Override
    public void delete(Long videoId) {
        baseMapper.deleteById(videoId);
    }


}
