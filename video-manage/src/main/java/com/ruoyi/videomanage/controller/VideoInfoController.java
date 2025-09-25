package com.ruoyi.videomanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.videomanage.domain.VideoInfo;
import com.ruoyi.videomanage.service.IVideoInfoService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频文件信息Controller
 *
 * @author ruoyi
 * @date 2025-09-25
 */
@RestController
@RequestMapping("/videomanage/video")
public class VideoInfoController extends BaseController
{
    @Autowired
    private IVideoInfoService videoInfoService;

    /**
     * 执行扫描任务，扫描指定媒体库路径下的视频文件并入库
     */
    @PreAuthorize("@ss.hasPermi('videomanage:video:scan')")
    @PostMapping("/scan")
    public AjaxResult scan(@RequestBody Map<String, Object> body)
    {
        Long libraryId = Long.valueOf(body.get("libraryId").toString());
        String libraryPath = body.get("libraryPath").toString();

        if (libraryPath == null || libraryPath.isEmpty()) {
            return AjaxResult.error("媒体库路径不能为空");
        }

        int count = videoInfoService.scanVideosAndSaveToDb(libraryId, libraryPath);
        return AjaxResult.success("扫描完成，本次新增 " + count + " 个视频文件。");
    }

    /**
     * 查询视频文件信息列表
     */
    @PreAuthorize("@ss.hasPermi('videomanage:video:list')")
    @GetMapping("/list")
    public TableDataInfo list(VideoInfo videoInfo)
    {
        startPage();
        List<VideoInfo> list = videoInfoService.selectVideoInfoList(videoInfo);
        return getDataTable(list);
    }
}
