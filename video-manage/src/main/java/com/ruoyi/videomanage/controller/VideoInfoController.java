package com.ruoyi.videomanage.controller;

import java.util.List;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.domain.Result;
import com.ruoyi.common.core.page.PageResponse;

import com.ruoyi.videomanage.domain.dto.VideoInfoSaveRequest;
import com.ruoyi.videomanage.domain.dto.VideoInfoQueryRequest;
import com.ruoyi.videomanage.domain.vo.VideoInfoQueryResponse;
import com.ruoyi.videomanage.service.VideoInfoService;

/**
 * 视频文件信息Controller
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@RestController
@RequestMapping("/admin/video_info")
public class VideoInfoController {

    @Resource
    private VideoInfoService videoInfoService;

    @PostMapping("/save")
    public Result<Object> save(@Valid @RequestBody VideoInfoSaveRequest request) {
        videoInfoService.save(request);
        return Result.success();
    }

    @GetMapping("/query-list")
    public Result<PageResponse<VideoInfoQueryResponse>> queryList(@Valid VideoInfoQueryRequest request) {
        PageResponse<VideoInfoQueryResponse> list = videoInfoService.queryList(request);
        return Result.success(list);
    }

    @DeleteMapping("/delete/{videoId}")
    public Result<Object> delete(@PathVariable Long videoId) {
        videoInfoService.delete(videoId);
        return Result.success();
    }

    @DeleteMapping("/batchDelete")
    public Result<Object> batchDelete(@RequestBody List<Long> videoIds) {
        videoInfoService.removeByIds(videoIds);
        return Result.success();
    }
}
