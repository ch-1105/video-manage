package com.ruoyi.videomanage.service.impl;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.videomanage.domain.VideoInfo;
import com.ruoyi.videomanage.utils.FFmpegUtils;
import com.ruoyi.videomanage.utils.FileUtil;
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

import com.ruoyi.videomanage.mapper.MediaLibraryMapper;
import com.ruoyi.videomanage.domain.MediaLibrary;
import com.ruoyi.videomanage.domain.dto.MediaLibrarySaveRequest;
import com.ruoyi.videomanage.domain.dto.MediaLibraryQueryRequest;
import com.ruoyi.videomanage.domain.vo.MediaLibraryQueryResponse;
import com.ruoyi.videomanage.service.MediaLibraryService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 本地文件夹Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class MediaLibraryServiceImpl extends ServiceImpl<MediaLibraryMapper, MediaLibrary> implements MediaLibraryService
{
    private static final Logger log = LoggerFactory.getLogger(MediaLibraryService.class);

    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList(".mp4", ".mkv", ".avi", ".mov", ".wmv", ".flv");

    @Resource
    private VideoInfoServiceImpl videoInfoService;

    @Resource
    private MediaLibraryMapper mediaLibraryMapper;

    @Resource
    private FFmpegUtils ffmpegUtils;

    @Override
    public void save(MediaLibrarySaveRequest request) {
        DateTime now = DateTime.now();
        MediaLibrary mediaLibrary = BeanUtil.copyProperties(request, MediaLibrary.class);
        if (ObjectUtil.isNull(mediaLibrary.getLibraryId())) {
            mediaLibrary.setLibraryId(IdUtil.getSnowflakeNextId());
            mediaLibrary.setCreateTime(now);
            mediaLibrary.setUpdateTime(now);
            baseMapper.insert(mediaLibrary);
        } else {
            mediaLibrary.setUpdateTime(now);
            baseMapper.updateById(mediaLibrary);
        }
    }

    @Override
    public PageResponse<MediaLibraryQueryResponse> queryList(MediaLibraryQueryRequest request) {
        QueryWrapper<MediaLibrary> mediaLibraryWrapper = new QueryWrapper<>();
        mediaLibraryWrapper.orderByDesc("library_id");

        log.info("查询页码：{}", request.getPageNum());
        log.info("每页条数：{}", request.getPageSize());
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<MediaLibrary> mediaLibraryList = baseMapper.selectList(mediaLibraryWrapper);

        PageInfo<MediaLibrary> pageInfo = new PageInfo<>(mediaLibraryList);
        log.info("总行数：{}", pageInfo.getTotal());
        log.info("总页数：{}", pageInfo.getPages());

        List<MediaLibraryQueryResponse> list = BeanUtil.copyToList(mediaLibraryList, MediaLibraryQueryResponse.class);

        PageResponse<MediaLibraryQueryResponse> pageResponse = new PageResponse<>();
        pageResponse.setTotal(pageInfo.getTotal());
        pageResponse.setList(list);
        return pageResponse;
    }

    @Override
    public void delete(Long libraryId) {
        baseMapper.deleteById(libraryId);
    }

    /**
     * 主入口方法：扫描指定的媒体库
     */
    @Transactional
    @Override
    public void scanLibrary(Long libraryId) {
        // 1. 获取媒体库根路径
        MediaLibrary library = this.getById(libraryId);
        if (library == null || !new File(library.getLibraryPath()).exists()) {
            throw new RuntimeException("媒体库路径不存在!");
        }
        String rootPath = library.getLibraryPath();

        // 2. 获取数据库中已存在的该库的视频路径
        Set<String> dbFilePaths = videoInfoService.list(
                        new LambdaQueryWrapper<VideoInfo>()
                                .eq(VideoInfo::getLibraryId, libraryId)
                ).stream()
                .map(VideoInfo::getFilePath)
                .collect(Collectors.toSet());

        // 3. 递归扫描物理文件系统，获取当前所有视频文件路径
        List<File> diskFiles = new ArrayList<>();
        scanDirectoryRecursively(new File(rootPath), diskFiles);
        Set<String> diskFilePaths = diskFiles.stream()
                .map(File::getAbsolutePath)
                .collect(Collectors.toSet());

        // 4. 计算差异：找出新增的和已删除的
        // 新增的文件 = 磁盘上的文件 - 数据库中的文件
        List<File> newFiles = diskFiles.stream()
                .filter(file -> !dbFilePaths.contains(file.getAbsolutePath()))
                .collect(Collectors.toList());

        // 已删除的文件路径 = 数据库中的文件 - 磁盘上的文件
        Set<String> deletedFilePaths = new HashSet<>(dbFilePaths);
        deletedFilePaths.removeAll(diskFilePaths);

        // 5. 处理新增文件
        if (!newFiles.isEmpty()) {
            List<VideoInfo> newVideoInfos = new ArrayList<>();
            for (File file : newFiles) {
                // 使用FFmpeg获取视频信息
                VideoInfo videoInfo = null;
                try {
                    videoInfo = ffmpegUtils.getVideoInfo(file.getAbsolutePath());
                } catch (IOException | InterruptedException e) {
                    throw new ServiceException("获取视频信息出现错误");
                }
                if (videoInfo != null) {
                    videoInfo.setLibraryId(libraryId);
                    videoInfo.setFileName(file.getName());
                    videoInfo.setFilePath(file.getAbsolutePath());
                    videoInfo.setStatus("0");
                    try {
                        videoInfo.setFileHash(FileUtil.getFileMd5(file));
                    } catch (IOException | NoSuchAlgorithmException e) {
                        throw new ServiceException("获取文件md5信息出现错误");
                    }

                    videoInfo.setFileSize(String.valueOf(file.length()));
                    newVideoInfos.add(videoInfo);
                }
            }
            // 批量插入数据库
            if (!newVideoInfos.isEmpty()) {
                videoInfoService.saveBatch(newVideoInfos);
            }
        }

        // 6. 处理已删除文件
        if (!deletedFilePaths.isEmpty()) {
            List<VideoInfo> videosToUpdate = videoInfoService.list(
                    new LambdaQueryWrapper<VideoInfo>()
                            .eq(VideoInfo::getLibraryId, libraryId)
                            .in(VideoInfo::getFilePath, deletedFilePaths)
            );

            videosToUpdate.forEach(video -> video.setStatus("1")); // 0=正常, 1=文件丢失

            // 批量更新状态
            if (!videosToUpdate.isEmpty()) {
                videoInfoService.updateBatchById(videosToUpdate);
            }
        }
    }

    /**
     * 递归扫描辅助方法
     * @param directory 当前目录
     * @param videoFilesFound 存储找到的视频文件
     */
    private void scanDirectoryRecursively(File directory, List<File> videoFilesFound) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                scanDirectoryRecursively(file, videoFilesFound); // 如果是目录，则递归
            } else {
                // 如果是文件，判断扩展名是否为支持的视频格式
                String fileName = file.getName().toLowerCase();
                for (String ext : SUPPORTED_EXTENSIONS) {
                    if (fileName.endsWith(ext)) {
                        videoFilesFound.add(file);
                        break;
                    }
                }
            }
        }
    }
}
