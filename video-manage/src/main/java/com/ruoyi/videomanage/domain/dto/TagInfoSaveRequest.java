package com.ruoyi.videomanage.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 标签信息保存请求对象
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Data
public class TagInfoSaveRequest {

    /**
     * 标签ID
     */
    private Long tagId;

    /**
     * 标签名称
     */
    @NotBlank(message = "【标签名称】不能为空")
    private String tagName;

}
