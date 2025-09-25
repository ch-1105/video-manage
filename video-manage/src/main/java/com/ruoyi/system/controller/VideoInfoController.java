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
import com.ruoyi.system.domain.VideoInfo;
import com.ruoyi.system.service.IVideoInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频文件信息Controller
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
@RestController
@RequestMapping("/video/videoInfo")
public class VideoInfoController extends BaseController
{
    @Autowired
    private IVideoInfoService videoInfoService;

    /**
     * 查询视频文件信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(VideoInfo videoInfo)
    {
        startPage();
        List<VideoInfo> list = videoInfoService.selectVideoInfoList(videoInfo);
        return getDataTable(list);
    }

    /**
     * 导出视频文件信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "视频文件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VideoInfo videoInfo)
    {
        List<VideoInfo> list = videoInfoService.selectVideoInfoList(videoInfo);
        ExcelUtil<VideoInfo> util = new ExcelUtil<VideoInfo>(VideoInfo.class);
        util.exportExcel(response, list, "视频文件信息数据");
    }

    /**
     * 获取视频文件信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{videoId}")
    public AjaxResult getInfo(@PathVariable("videoId") Long videoId)
    {
        return success(videoInfoService.selectVideoInfoByVideoId(videoId));
    }

    /**
     * 新增视频文件信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "视频文件信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VideoInfo videoInfo)
    {
        return toAjax(videoInfoService.insertVideoInfo(videoInfo));
    }

    /**
     * 修改视频文件信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "视频文件信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VideoInfo videoInfo)
    {
        return toAjax(videoInfoService.updateVideoInfo(videoInfo));
    }

    /**
     * 删除视频文件信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "视频文件信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{videoIds}")
    public AjaxResult remove(@PathVariable Long[] videoIds)
    {
        return toAjax(videoInfoService.deleteVideoInfoByVideoIds(videoIds));
    }
}
