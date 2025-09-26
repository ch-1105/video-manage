package com.ruoyi.videomanage.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 标签信息查询返回对象
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Data
public class TagInfoQueryResponse {

    /**
     * 标签ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;

    /**
     * 标签名称
     */
    private String tagName;

}
