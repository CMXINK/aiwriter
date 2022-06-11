package com.dawn.aiwriter.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Summary {
    private static final long serialVersionUID = 1L;

    private Long summaryId;

    private String summary;

    private Long articleId;

    private char status;

    private char delFlag;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;
}
