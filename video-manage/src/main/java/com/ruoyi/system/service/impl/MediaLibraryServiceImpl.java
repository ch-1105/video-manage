package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MediaLibraryMapper;
import com.ruoyi.system.domain.MediaLibrary;
import com.ruoyi.system.service.IMediaLibraryService;

/**
 * 本地文件夹Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-10
 */
@Service
public class MediaLibraryServiceImpl implements IMediaLibraryService 
{
    @Autowired
    private MediaLibraryMapper mediaLibraryMapper;

    /**
     * 查询本地文件夹
     * 
     * @param libraryId 本地文件夹主键
     * @return 本地文件夹
     */
    @Override
    public MediaLibrary selectMediaLibraryByLibraryId(Long libraryId)
    {
        return mediaLibraryMapper.selectMediaLibraryByLibraryId(libraryId);
    }

    /**
     * 查询本地文件夹列表
     * 
     * @param mediaLibrary 本地文件夹
     * @return 本地文件夹
     */
    @Override
    public List<MediaLibrary> selectMediaLibraryList(MediaLibrary mediaLibrary)
    {
        return mediaLibraryMapper.selectMediaLibraryList(mediaLibrary);
    }

    /**
     * 新增本地文件夹
     * 
     * @param mediaLibrary 本地文件夹
     * @return 结果
     */
    @Override
    public int insertMediaLibrary(MediaLibrary mediaLibrary)
    {
        mediaLibrary.setCreateTime(DateUtils.getNowDate());
        return mediaLibraryMapper.insertMediaLibrary(mediaLibrary);
    }

    /**
     * 修改本地文件夹
     * 
     * @param mediaLibrary 本地文件夹
     * @return 结果
     */
    @Override
    public int updateMediaLibrary(MediaLibrary mediaLibrary)
    {
        mediaLibrary.setUpdateTime(DateUtils.getNowDate());
        return mediaLibraryMapper.updateMediaLibrary(mediaLibrary);
    }

    /**
     * 批量删除本地文件夹
     * 
     * @param libraryIds 需要删除的本地文件夹主键
     * @return 结果
     */
    @Override
    public int deleteMediaLibraryByLibraryIds(Long[] libraryIds)
    {
        return mediaLibraryMapper.deleteMediaLibraryByLibraryIds(libraryIds);
    }

    /**
     * 删除本地文件夹信息
     * 
     * @param libraryId 本地文件夹主键
     * @return 结果
     */
    @Override
    public int deleteMediaLibraryByLibraryId(Long libraryId)
    {
        return mediaLibraryMapper.deleteMediaLibraryByLibraryId(libraryId);
    }
}
