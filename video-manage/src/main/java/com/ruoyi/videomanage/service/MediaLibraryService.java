package com.ruoyi.videomanage.service;

import com.ruoyi.videomanage.domain.MediaLibrary;
import com.ruoyi.videomanage.domain.dto.MediaLibrarySaveRequest;
import com.ruoyi.videomanage.domain.dto.MediaLibraryQueryRequest;
import com.ruoyi.videomanage.domain.vo.MediaLibraryQueryResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.page.PageResponse;
import org.springframework.transaction.annotation.Transactional;

/**
 * 本地文件夹Service接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface MediaLibraryService extends IService<MediaLibrary>
{
    /**
     * 保存本地文件夹
     * 
     * @param request 本地文件夹保存请求
     */
    void save(MediaLibrarySaveRequest request);

    /**
     * 查询本地文件夹列表
     * 
     * @param request 本地文件夹查询请求
     * @return 本地文件夹集合
     */
    PageResponse<MediaLibraryQueryResponse> queryList(MediaLibraryQueryRequest request);

    /**
     * 删除本地文件夹
     * 
     * @param libraryId 本地文件夹主键
     */
    void delete(Long libraryId);

    @Transactional
        // 保证整个扫描过程的事务性
    void scanLibrary(Long libraryId);
}
