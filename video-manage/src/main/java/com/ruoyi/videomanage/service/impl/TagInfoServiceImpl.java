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

import com.ruoyi.videomanage.mapper.TagInfoMapper;
import com.ruoyi.videomanage.domain.TagInfo;
import com.ruoyi.videomanage.domain.dto.TagInfoSaveRequest;
import com.ruoyi.videomanage.domain.dto.TagInfoQueryRequest;
import com.ruoyi.videomanage.domain.vo.TagInfoQueryResponse;
import com.ruoyi.videomanage.service.TagInfoService;

/**
 * 标签信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class TagInfoServiceImpl extends ServiceImpl<TagInfoMapper, TagInfo> implements TagInfoService
{
    private static final Logger log = LoggerFactory.getLogger(TagInfoService.class);

    @Resource
    private TagInfoMapper tagInfoMapper;

    @Override
    public void save(TagInfoSaveRequest request) {
        DateTime now = DateTime.now();
        TagInfo tagInfo = BeanUtil.copyProperties(request, TagInfo.class);
        if (ObjectUtil.isNull(tagInfo.getTagId())) {
            tagInfo.setTagId(IdUtil.getSnowflakeNextId());
            tagInfo.setCreateTime(now);
            tagInfo.setUpdateTime(now);
            baseMapper.insert(tagInfo);
        } else {
            tagInfo.setUpdateTime(now);
            baseMapper.updateById(tagInfo);
        }
    }

    @Override
    public PageResponse<TagInfoQueryResponse> queryList(TagInfoQueryRequest request) {
        QueryWrapper<TagInfo> tagInfoWrapper = new QueryWrapper<>();
        tagInfoWrapper.orderByDesc("tag_id");

        log.info("查询页码：{}", request.getPageNum());
        log.info("每页条数：{}", request.getPageSize());
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<TagInfo> tagInfoList = baseMapper.selectList(tagInfoWrapper);

        PageInfo<TagInfo> pageInfo = new PageInfo<>(tagInfoList);
        log.info("总行数：{}", pageInfo.getTotal());
        log.info("总页数：{}", pageInfo.getPages());

        List<TagInfoQueryResponse> list = BeanUtil.copyToList(tagInfoList, TagInfoQueryResponse.class);

        PageResponse<TagInfoQueryResponse> pageResponse = new PageResponse<>();
        pageResponse.setTotal(pageInfo.getTotal());
        pageResponse.setList(list);
        return pageResponse;
    }

    @Override
    public void delete(Long tagId) {
        baseMapper.deleteById(tagId);
    }
}
