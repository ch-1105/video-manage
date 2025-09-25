package com.ruoyi.system.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.VideoTagRelation;
import com.ruoyi.system.service.IVideoTagRelationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频与标签关联Controller
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
@RestController
@RequestMapping("/video/relation")
public class VideoTagRelationController extends BaseController
{
    @Autowired
    private IVideoTagRelationService videoTagRelationService;

    /**
     * 查询视频与标签关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(VideoTagRelation videoTagRelation)
    {
        startPage();
        List<VideoTagRelation> list = videoTagRelationService.selectVideoTagRelationList(videoTagRelation);
        return getDataTable(list);
    }

    /**
     * 导出视频与标签关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:relation:export')")
    @Log(title = "视频与标签关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VideoTagRelation videoTagRelation)
    {
        List<VideoTagRelation> list = videoTagRelationService.selectVideoTagRelationList(videoTagRelation);
        ExcelUtil<VideoTagRelation> util = new ExcelUtil<VideoTagRelation>(VideoTagRelation.class);
        util.exportExcel(response, list, "视频与标签关联数据");
    }

    /**
     * 获取视频与标签关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:relation:query')")
    @GetMapping(value = "/{videoId}")
    public AjaxResult getInfo(@PathVariable("videoId") Long videoId)
    {
        return success(videoTagRelationService.selectVideoTagRelationByVideoId(videoId));
    }

    /**
     * 新增视频与标签关联
     */
    @PreAuthorize("@ss.hasPermi('system:relation:add')")
    @Log(title = "视频与标签关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VideoTagRelation videoTagRelation)
    {
        return toAjax(videoTagRelationService.insertVideoTagRelation(videoTagRelation));
    }

    /**
     * 修改视频与标签关联
     */
    @PreAuthorize("@ss.hasPermi('system:relation:edit')")
    @Log(title = "视频与标签关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VideoTagRelation videoTagRelation)
    {
        return toAjax(videoTagRelationService.updateVideoTagRelation(videoTagRelation));
    }

    /**
     * 删除视频与标签关联
     */
    @PreAuthorize("@ss.hasPermi('system:relation:remove')")
    @Log(title = "视频与标签关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{videoIds}")
    public AjaxResult remove(@PathVariable Long[] videoIds)
    {
        return toAjax(videoTagRelationService.deleteVideoTagRelationByVideoIds(videoIds));
    }
}
