package com.ruoyi.videomanage.controller;

import java.util.List;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.domain.Result;
import com.ruoyi.common.core.page.PageResponse;

import com.ruoyi.videomanage.domain.dto.MediaLibrarySaveRequest;
import com.ruoyi.videomanage.domain.dto.MediaLibraryQueryRequest;
import com.ruoyi.videomanage.domain.vo.MediaLibraryQueryResponse;
import com.ruoyi.videomanage.service.MediaLibraryService;

/**
 * 本地文件夹Controller
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@RestController
@RequestMapping("/admin/media_library")
public class MediaLibraryController {

    @Resource
    private MediaLibraryService mediaLibraryService;

    @PostMapping("/save")
    public Result<Object> save(@Valid @RequestBody MediaLibrarySaveRequest request) {
        mediaLibraryService.save(request);
        return Result.success();
    }

    @GetMapping("/query-list")
    public Result<PageResponse<MediaLibraryQueryResponse>> queryList(@Valid MediaLibraryQueryRequest request) {
        PageResponse<MediaLibraryQueryResponse> list = mediaLibraryService.queryList(request);
        return Result.success(list);
    }

    @DeleteMapping("/delete/{libraryId}")
    public Result<Object> delete(@PathVariable Long libraryId) {
        mediaLibraryService.delete(libraryId);
        return Result.success();
    }

    @DeleteMapping("/batchDelete")
    public Result<Object> batchDelete(@RequestBody List<Long> libraryIds) {
        mediaLibraryService.removeByIds(libraryIds);
        return Result.success();
    }
}
