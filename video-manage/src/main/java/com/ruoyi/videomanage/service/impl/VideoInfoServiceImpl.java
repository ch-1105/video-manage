//package com.ruoyi.videomanage.service.impl;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import com.ruoyi.common.utils.DateUtils;
//import com.ruoyi.common.utils.SecurityUtils;
//import com.ruoyi.common.utils.file.FileScanUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.ruoyi.videomanage.mapper.VideoInfoMapper;
//import com.ruoyi.videomanage.domain.VideoInfo;
//import com.ruoyi.videomanage.service.IVideoInfoService;
//
///**
// * 视频文件信息Service业务层处理
// *
// * @author ruoyi
// * @date 2025-09-25
// */
//@Service
//public class VideoInfoServiceImpl implements IVideoInfoService
//{
//    private static final Logger log = LoggerFactory.getLogger(VideoInfoServiceImpl.class);
//
//    @Autowired
//    private VideoInfoMapper videoInfoMapper;
//
//    /**
//     * 根据媒体库ID和路径扫描视频文件并入库
//     *
//     * @param libraryId 媒体库ID
//     * @param path 媒体库物理路径
//     * @return 本次新入库的视频数量
//     */
//    @Override
//    public int scanVideosAndSaveToDb(Long libraryId, String path)
//    {
//        // 1. 调用工具类扫描物理文件
//        List<Path> scannedFiles;
//        try {
//            scannedFiles = FileScanUtils.scanVideoFiles(path);
//        } catch (IOException e) {
//            log.error("扫描视频文件时发生IO异常: ", e);
//            throw new RuntimeException("文件扫描失败，请检查路径或权限");
//        }
//
//        if (scannedFiles.isEmpty()) {
//            return 0;
//        }
//
//        // 2. 从数据库查询该媒体库下所有已存在的记录的路径
//        Set<String> existingPaths = videoInfoMapper.selectFilePathsByLibraryId(libraryId)
//                .stream()
//                .collect(Collectors.toSet());
//
//        // 3. 过滤出本次扫描到的、但数据库中不存在的新文件
//        List<VideoInfo> newVideoInfos = new ArrayList<>();
//        String username = SecurityUtils.getUsername();
//
//        for (Path file : scannedFiles) {
//            String absolutePath = file.toAbsolutePath().toString();
//            if (!existingPaths.contains(absolutePath)) {
//                VideoInfo videoInfo = new VideoInfo();
//                try {
//                    videoInfo.setLibraryId(libraryId);
//                    videoInfo.setFileName(file.getFileName().toString());
//                    videoInfo.setFilePath(absolutePath);
//                    videoInfo.setFileSize(Files.size(file));
//                    // 获取文件后缀作为格式名
//                    String fileName = file.getFileName().toString();
//                    int lastDotIndex = fileName.lastIndexOf('.');
//                    if (lastDotIndex > 0) {
//                        videoInfo.setFormatName(fileName.substring(lastDotIndex + 1));
//                    }
//
//                    videoInfo.setStatus("0"); // 默认正常
//                    videoInfo.setCreateBy(username);
//                    newVideoInfos.add(videoInfo);
//                } catch (IOException e) {
//                    log.error("获取文件大小失败: {}", absolutePath, e);
//                    // 可选择跳过这个文件或做其他处理
//                }
//            }
//        }
//
//        // 4. 如果有新文件，则批量插入数据库
//        if (!newVideoInfos.isEmpty()) {
//            return videoInfoMapper.batchInsertVideoInfo(newVideoInfos);
//        }
//
//        return 0;
//    }
//
//    /**
//     * 查询视频文件信息列表
//     *
//     * @param videoInfo 视频文件信息
//     * @return 视频文件信息
//     */
//    @Override
//    public List<VideoInfo> selectVideoInfoList(VideoInfo videoInfo)
//    {
//        return videoInfoMapper.selectVideoInfoList(videoInfo);
//    }
//}
