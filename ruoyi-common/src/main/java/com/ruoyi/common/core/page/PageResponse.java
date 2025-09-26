package com.ruoyi.common.core.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResponse<T> implements Serializable {
    //总条数
    private Long total;

    //返回的封装列表
    private List<T> list;
}
