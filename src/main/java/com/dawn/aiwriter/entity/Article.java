package com.dawn.aiwriter.entity;

import lombok.Data;

import java.util.Date;

/**
 * 文档实体类
 *
 * @author Dawn
 * @date 2022/05/25
 */
@Data
public class Article {
    private static final long serialVersionUID = 1L;

    /**编号*/
    private Long id;

    /**标题*/
    private String title;

    /**摘要*/
    private String summary;

    /**文章内容*/
    private String articleBody;

    /**文章字数*/
    private String wordNum;

    /**用户id*/
    private Long userId;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;
}
