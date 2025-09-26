package com.ruoyi.videomanage.service.impl;

import java.util.List;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ruoyi.videomanage.mapper.VideoTagRelationMapper;
import com.ruoyi.videomanage.domain.VideoTagRelation;
import com.ruoyi.videomanage.domain.dto.VideoTagRelationSaveRequest;
import com.ruoyi.videomanage.domain.dto.VideoTagRelationQueryRequest;
import com.ruoyi.videomanage.domain.vo.VideoTagRelationQueryResponse;
import com.ruoyi.videomanage.service.VideoTagRelationService;

/**
 * 视频与标签关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class VideoTagRelationServiceImpl extends ServiceImpl<VideoTagRelationMapper, VideoTagRelation> implements VideoTagRelationService
{
    private static final Logger log = LoggerFactory.getLogger(VideoTagRelationService.class);

    @Resource
    private VideoTagRelationMapper videoTagRelationMapper;

    @Override
    public void save(VideoTagRelationSaveRequest request) {
        DateTime now = DateTime.now();
        VideoTagRelation videoTagRelation = BeanUtil.copyProperties(request, VideoTagRelation.class);
        if (ObjectUtil.isNull(videoTagRelation.getVideoId())) {
            videoTagRelation.setVideoId(IdUtil.getSnowflakeNextId());
            videoTagRelation.setCreateTime(now);
            videoTagRelation.setUpdateTime(now);
            baseMapper.insert(videoTagRelation);
        } else {
            videoTagRelation.setUpdateTime(now);
            baseMapper.updateById(videoTagRelation);
        }
    }

    @Override
    public PageResponse<VideoTagRelationQueryResponse> queryList(VideoTagRelationQueryRequest request) {
        QueryWrapper<VideoTagRelation> videoTagRelationWrapper = new QueryWrapper<>();
        videoTagRelationWrapper.orderByDesc("video_id");

        log.info("查询页码：{}", request.getPageNum());
        log.info("每页条数：{}", request.getPageSize());
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<VideoTagRelation> videoTagRelationList = baseMapper.selectList(videoTagRelationWrapper);

        PageInfo<VideoTagRelation> pageInfo = new PageInfo<>(videoTagRelationList);
        log.info("总行数：{}", pageInfo.getTotal());
        log.info("总页数：{}", pageInfo.getPages());

        List<VideoTagRelationQueryResponse> list = BeanUtil.copyToList(videoTagRelationList, VideoTagRelationQueryResponse.class);

        PageResponse<VideoTagRelationQueryResponse> pageResponse = new PageResponse<>();
        pageResponse.setTotal(pageInfo.getTotal());
        pageResponse.setList(list);
        return pageResponse;
    }

    @Override
    public void delete(Long videoId) {
        baseMapper.deleteById(videoId);
    }
}
