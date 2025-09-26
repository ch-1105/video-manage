package com.ruoyi.videomanage.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtil {
    private static final int BUFFER_SIZE = 8192; // 8KB缓冲
    private static final String ALGORITHM = "MD5";

    /**
     * 计算本地文件的MD5值
     * @param file 本地文件
     * @return MD5哈希值（32位小写）
     * @throws IOException 文件读写异常
     * @throws NoSuchAlgorithmException 不支持MD5算法（理论上不会发生）
     */
    public static String getFileMd5(File file) throws IOException, NoSuchAlgorithmException {
        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在: " + file.getAbsolutePath());
        }
        if (!file.isFile()) {
            throw new IOException("不是文件: " + file.getAbsolutePath());
        }

        MessageDigest md5 = MessageDigest.getInstance(ALGORITHM);
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis, BUFFER_SIZE)) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                md5.update(buffer, 0, bytesRead);
            }
        }

        return bytesToHex(md5.digest());
    }

    /**
     * 计算上传文件（MultipartFile）的MD5值
     * @param multipartFile 上传的文件
     * @return MD5哈希值（32位小写）
     * @throws IOException 流操作异常
     * @throws NoSuchAlgorithmException 不支持MD5算法
     */
    public static String getMultipartFileMd5(MultipartFile multipartFile) throws IOException, NoSuchAlgorithmException {
        if (multipartFile.isEmpty()) {
            throw new IOException("上传文件为空");
        }

        MessageDigest md5 = MessageDigest.getInstance(ALGORITHM);
        try (InputStream is = multipartFile.getInputStream();
             BufferedInputStream bis = new BufferedInputStream(is, BUFFER_SIZE)) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                md5.update(buffer, 0, bytesRead);
            }
        }

        return bytesToHex(md5.digest());
    }

    /**
     * 将字节数组转换为32位小写MD5字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            // 转换为两位十六进制数，不足两位前面补0
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
