package com.ruoyi.videomanage.controller;

import java.util.List;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.domain.Result;
import com.ruoyi.common.core.page.PageResponse;

import com.ruoyi.videomanage.domain.dto.TagInfoSaveRequest;
import com.ruoyi.videomanage.domain.dto.TagInfoQueryRequest;
import com.ruoyi.videomanage.domain.vo.TagInfoQueryResponse;
import com.ruoyi.videomanage.service.TagInfoService;

/**
 * 标签信息Controller
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@RestController
@RequestMapping("/admin/tag_info")
public class TagInfoController {

    @Resource
    private TagInfoService tagInfoService;

    @PostMapping("/save")
    public Result<Object> save(@Valid @RequestBody TagInfoSaveRequest request) {
        tagInfoService.save(request);
        return Result.success();
    }

    @GetMapping("/query-list")
    public Result<PageResponse<TagInfoQueryResponse>> queryList(@Valid TagInfoQueryRequest request) {
        PageResponse<TagInfoQueryResponse> list = tagInfoService.queryList(request);
        return Result.success(list);
    }

    @DeleteMapping("/delete/{tagId}")
    public Result<Object> delete(@PathVariable Long tagId) {
        tagInfoService.delete(tagId);
        return Result.success();
    }

    @DeleteMapping("/batchDelete")
    public Result<Object> batchDelete(@RequestBody List<Long> tagIds) {
        tagInfoService.removeByIds(tagIds);
        return Result.success();
    }
}
