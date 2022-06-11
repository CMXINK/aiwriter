package com.dawn.aiwriter.service;

import com.dawn.aiwriter.entity.Article;

import java.util.ArrayList;
import java.util.List;

public interface IArticleService {
    /**
     * 查询文章列表
     * @param
     * @return 文章列表
     */
    public List<Article> selectArticleList();

    Integer insertArticle(Article article);

    Integer deleteArticle(Long id);

    Integer updateArticle(Article article);

    Article selectArticleById(Long id);

    /**
     * 计算文章字数
     * @param article
     * @return 字数
     */
    Integer countWords(Article article);
}
