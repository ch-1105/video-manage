package com.ruoyi.common.utils.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 文件扫描工具类
 */
public class FileScanUtils {

    /**
     * 支持的视频文件扩展名
     * 这里可以根据需要添加更多格式
     */
    private static final List<String> SUPPORTED_VIDEO_EXTENSIONS = Arrays.asList(
            ".mp4", ".mkv", ".avi", ".mov", ".wmv", ".flv", ".rmvb", ".webm"
    );

    /**
     * 递归扫描指定路径下的所有视频文件.
     *
     * @param directoryPath 要扫描的目录的绝对路径.
     * @return 视频文件的Path对象列表.
     * @throws IOException 如果在访问文件系统时发生错误.
     */
    public static List<Path> scanVideoFiles(String directoryPath) throws IOException {
        Path startPath = Paths.get(directoryPath);

        if (!Files.isDirectory(startPath)) {
            throw new IllegalArgumentException("提供的路径不是一个有效的目录: " + directoryPath);
        }

        try (Stream<Path> stream = Files.walk(startPath)) {
            return stream
                    .filter(Files::isRegularFile) // 只处理文件，忽略子目录
                    .filter(FileScanUtils::isVideoFile) // 筛选出视频文件
                    .collect(Collectors.toList());
        }
    }

    /**
     * 检查文件是否为支持的视频格式.
     *
     * @param path 文件的路径.
     * @return 如果是视频文件则返回 true, 否则返回 false.
     */
    private static boolean isVideoFile(Path path) {
        String fileName = path.getFileName().toString().toLowerCase();
        return SUPPORTED_VIDEO_EXTENSIONS.stream().anyMatch(fileName::endsWith);
    }
}
