package com.ruoyi.videomanage.utils;

import com.ruoyi.videomanage.domain.VideoInfo;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FFmpegUtils {
    // 执行FFmpeg命令
    public boolean executeCommand(String command) throws IOException, InterruptedException {
        // 拆分命令（避免空格导致的参数错误）
        String[] cmd = command.split(" ");

        ProcessBuilder processBuilder = new ProcessBuilder(cmd);
        // 合并错误流到输出流，便于统一处理
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        // 读取命令输出（必须读取，否则可能导致进程阻塞）
        try (InputStream inputStream = process.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 可根据需要打印日志或处理输出
                System.out.println("FFmpeg输出：" + line);
            }
        }

        // 等待命令执行完成（设置超时时间，避免无限等待）
        boolean isSuccess = process.waitFor(60, TimeUnit.SECONDS);
        // 销毁进程
        process.destroy();

        return isSuccess;
    }

    // 示例：视频转码（MP4转WebM）
    public boolean convertVideo(String inputPath, String outputPath) throws IOException, InterruptedException {
        // FFmpeg命令：ffmpeg -i 输入文件 -c:v libvpx -c:a libvorbis 输出文件
        String command = String.format("ffmpeg -i \"%s\" -c:v libvpx -c:a libvorbis \"%s\"",
                inputPath, outputPath);
        return executeCommand(command);
    }

    /**
     * 使用FFmpeg获取视频信息
     * @param videoPath 视频文件路径
     * @return 包含时长和分辨率的VideoInfo对象
     * @throws IOException 如果执行命令出错
     * @throws InterruptedException 如果进程被中断
     */
    public static VideoInfo getVideoInfo(String videoPath)
            throws IOException, InterruptedException {

        // 构建FFmpeg命令
        String command = String.format("ffmpeg -i \"%s\"", videoPath);

        // 执行命令
        ProcessBuilder pb = new ProcessBuilder(command.split(" "));
        pb.redirectErrorStream(true); // 将错误流合并到输入流
        Process process = pb.start();

        // 读取命令输出
        StringBuilder output = new StringBuilder();
        try (InputStream inputStream = process.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        // 等待进程执行完成
        int exitCode = process.waitFor();

        // 如果命令执行失败，抛出异常
        if (exitCode != 0 && exitCode != 1) { // FFmpeg通常返回0表示成功，1表示警告但有输出
            throw new IOException("FFmpeg执行失败，退出码: " + exitCode + "\n输出: " + output.toString());
        }

        // 解析输出获取时长和分辨率
        String duration = parseDuration(output.toString());
        String resolution = parseResolution(output.toString());

        if (duration == null || resolution == null) {
            throw new IOException("无法解析视频信息，FFmpeg输出: " + output.toString());
        }

        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setDuration(duration);
        videoInfo.setResolution(resolution);
        return videoInfo;
    }

    /**
     * 从FFmpeg输出中解析视频时长
     */
    private static String parseDuration(String ffmpegOutput) {
        // 正则表达式匹配时长，如: Duration: 01:23:45.67, 或 00:05:20.12
        Pattern pattern = Pattern.compile("Duration: (\\d+:\\d+:\\d+\\.\\d+)");
        Matcher matcher = pattern.matcher(ffmpegOutput);

        if (matcher.find()) {
            return matcher.group(1).substring(0, 8); // 去掉毫秒部分
        }
        return null;
    }

    /**
     * 从FFmpeg输出中解析视频分辨率
     */
    private static String parseResolution(String ffmpegOutput) {
        // 只匹配纯数字组成的分辨率格式（宽度x高度）
        // 确保x前后都是数字，并且前面有逗号或空格，后面有逗号或空格
        Pattern pattern = Pattern.compile("[,\\s](\\d+)x(\\d+)[,\\s]");
        Matcher matcher = pattern.matcher(ffmpegOutput);

        if (matcher.find()) {
            return matcher.group(1) + "x" + matcher.group(2);
        }
        return null;
    }

    // 测试方法
    public static void main(String[] args) {
        try {
            // 视频文件路径
            String videoPath = "C:\\Users\\ch\\Videos\\素材.mp4";
            // FFmpeg路径，如果已加入环境变量可以直接用"ffmpeg"
            String ffmpegPath = "ffmpeg";

            VideoInfo info = getVideoInfo(videoPath);
            System.out.println("视频信息: " + info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
