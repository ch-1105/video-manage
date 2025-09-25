package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MediaLibrary;

/**
 * 本地文件夹Service接口
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
public interface IMediaLibraryService 
{
    /**
     * 查询本地文件夹
     * 
     * @param libraryId 本地文件夹主键
     * @return 本地文件夹
     */
    public MediaLibrary selectMediaLibraryByLibraryId(Long libraryId);

    /**
     * 查询本地文件夹列表
     * 
     * @param mediaLibrary 本地文件夹
     * @return 本地文件夹集合
     */
    public List<MediaLibrary> selectMediaLibraryList(MediaLibrary mediaLibrary);

    /**
     * 新增本地文件夹
     * 
     * @param mediaLibrary 本地文件夹
     * @return 结果
     */
    public int insertMediaLibrary(MediaLibrary mediaLibrary);

    /**
     * 修改本地文件夹
     * 
     * @param mediaLibrary 本地文件夹
     * @return 结果
     */
    public int updateMediaLibrary(MediaLibrary mediaLibrary);

    /**
     * 批量删除本地文件夹
     * 
     * @param libraryIds 需要删除的本地文件夹主键集合
     * @return 结果
     */
    public int deleteMediaLibraryByLibraryIds(Long[] libraryIds);

    /**
     * 删除本地文件夹信息
     * 
     * @param libraryId 本地文件夹主键
     * @return 结果
     */
    public int deleteMediaLibraryByLibraryId(Long libraryId);
}
