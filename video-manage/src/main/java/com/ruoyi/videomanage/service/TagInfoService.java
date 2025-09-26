package com.ruoyi.videomanage.service;

import com.ruoyi.videomanage.domain.TagInfo;
import com.ruoyi.videomanage.domain.dto.TagInfoSaveRequest;
import com.ruoyi.videomanage.domain.dto.TagInfoQueryRequest;
import com.ruoyi.videomanage.domain.vo.TagInfoQueryResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.page.PageResponse;

/**
 * 标签信息Service接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface TagInfoService extends IService<TagInfo>
{
    /**
     * 保存标签信息
     * 
     * @param request 标签信息保存请求
     */
    void save(TagInfoSaveRequest request);

    /**
     * 查询标签信息列表
     * 
     * @param request 标签信息查询请求
     * @return 标签信息集合
     */
    PageResponse<TagInfoQueryResponse> queryList(TagInfoQueryRequest request);

    /**
     * 删除标签信息
     * 
     * @param tagId 标签信息主键
     */
    void delete(Long tagId);
}
