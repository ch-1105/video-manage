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
import com.ruoyi.system.domain.MediaLibrary;
import com.ruoyi.system.service.IMediaLibraryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 本地文件夹Controller
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
@RestController
@RequestMapping("/video/library")
public class MediaLibraryController extends BaseController
{
    @Autowired
    private IMediaLibraryService mediaLibraryService;

    /**
     * 查询本地文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('system:library:list')")
    @GetMapping("/list")
    public TableDataInfo list(MediaLibrary mediaLibrary)
    {
        startPage();
        List<MediaLibrary> list = mediaLibraryService.selectMediaLibraryList(mediaLibrary);
        return getDataTable(list);
    }

    /**
     * 导出本地文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('system:library:export')")
    @Log(title = "本地文件夹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MediaLibrary mediaLibrary)
    {
        List<MediaLibrary> list = mediaLibraryService.selectMediaLibraryList(mediaLibrary);
        ExcelUtil<MediaLibrary> util = new ExcelUtil<MediaLibrary>(MediaLibrary.class);
        util.exportExcel(response, list, "本地文件夹数据");
    }

    /**
     * 获取本地文件夹详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:library:query')")
    @GetMapping(value = "/{libraryId}")
    public AjaxResult getInfo(@PathVariable("libraryId") Long libraryId)
    {
        return success(mediaLibraryService.selectMediaLibraryByLibraryId(libraryId));
    }

    /**
     * 新增本地文件夹
     */
    @PreAuthorize("@ss.hasPermi('system:library:add')")
    @Log(title = "本地文件夹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MediaLibrary mediaLibrary)
    {
        return toAjax(mediaLibraryService.insertMediaLibrary(mediaLibrary));
    }

    /**
     * 修改本地文件夹
     */
    @PreAuthorize("@ss.hasPermi('system:library:edit')")
    @Log(title = "本地文件夹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MediaLibrary mediaLibrary)
    {
        return toAjax(mediaLibraryService.updateMediaLibrary(mediaLibrary));
    }

    /**
     * 删除本地文件夹
     */
    @PreAuthorize("@ss.hasPermi('system:library:remove')")
    @Log(title = "本地文件夹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{libraryIds}")
    public AjaxResult remove(@PathVariable Long[] libraryIds)
    {
        return toAjax(mediaLibraryService.deleteMediaLibraryByLibraryIds(libraryIds));
    }
}
