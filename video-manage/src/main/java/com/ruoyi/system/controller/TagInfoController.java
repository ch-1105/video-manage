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
import com.ruoyi.system.domain.TagInfo;
import com.ruoyi.system.service.ITagInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 标签信息Controller
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
@RestController
@RequestMapping("/video/tagInfo")
public class TagInfoController extends BaseController
{
    @Autowired
    private ITagInfoService tagInfoService;

    /**
     * 查询标签信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(TagInfo tagInfo)
    {
        startPage();
        List<TagInfo> list = tagInfoService.selectTagInfoList(tagInfo);
        return getDataTable(list);
    }

    /**
     * 导出标签信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "标签信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TagInfo tagInfo)
    {
        List<TagInfo> list = tagInfoService.selectTagInfoList(tagInfo);
        ExcelUtil<TagInfo> util = new ExcelUtil<TagInfo>(TagInfo.class);
        util.exportExcel(response, list, "标签信息数据");
    }

    /**
     * 获取标签信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{tagId}")
    public AjaxResult getInfo(@PathVariable("tagId") Long tagId)
    {
        return success(tagInfoService.selectTagInfoByTagId(tagId));
    }

    /**
     * 新增标签信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "标签信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TagInfo tagInfo)
    {
        return toAjax(tagInfoService.insertTagInfo(tagInfo));
    }

    /**
     * 修改标签信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "标签信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TagInfo tagInfo)
    {
        return toAjax(tagInfoService.updateTagInfo(tagInfo));
    }

    /**
     * 删除标签信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "标签信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tagIds}")
    public AjaxResult remove(@PathVariable Long[] tagIds)
    {
        return toAjax(tagInfoService.deleteTagInfoByTagIds(tagIds));
    }
}
