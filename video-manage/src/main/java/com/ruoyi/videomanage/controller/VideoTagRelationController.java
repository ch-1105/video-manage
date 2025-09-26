package com.ruoyi.videomanage.controller;

import java.util.List;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.domain.Result;
import com.ruoyi.common.core.page.PageResponse;

import com.ruoyi.videomanage.domain.dto.VideoTagRelationSaveRequest;
import com.ruoyi.videomanage.domain.dto.VideoTagRelationQueryRequest;
import com.ruoyi.videomanage.domain.vo.VideoTagRelationQueryResponse;
import com.ruoyi.videomanage.service.VideoTagRelationService;

/**
 * 视频与标签关联Controller
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@RestController
@RequestMapping("/admin/tag_relation")
public class VideoTagRelationController {

    @Resource
    private VideoTagRelationService videoTagRelationService;

    @PostMapping("/save")
    public Result<Object> save(@Valid @RequestBody VideoTagRelationSaveRequest request) {
        videoTagRelationService.save(request);
        return Result.success();
    }

    @GetMapping("/query-list")
    public Result<PageResponse<VideoTagRelationQueryResponse>> queryList(@Valid VideoTagRelationQueryRequest request) {
        PageResponse<VideoTagRelationQueryResponse> list = videoTagRelationService.queryList(request);
        return Result.success(list);
    }

    @DeleteMapping("/delete/{videoId}")
    public Result<Object> delete(@PathVariable Long videoId) {
        videoTagRelationService.delete(videoId);
        return Result.success();
    }

    @DeleteMapping("/batchDelete")
    public Result<Object> batchDelete(@RequestBody List<Long> videoIds) {
        videoTagRelationService.removeByIds(videoIds);
        return Result.success();
    }
}
