package com.dawn.aiwriter.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Title {
    private static final long serialVersionUID = 1L;

    private Long titleId;

    private String title;

    private Long articleId;

    private char status;

    private char delFlag;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;
}
