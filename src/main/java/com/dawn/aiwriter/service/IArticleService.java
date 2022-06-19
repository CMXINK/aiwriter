package com.dawn.aiwriter.service;

import com.dawn.aiwriter.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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

    String importArticle(MultipartFile file);

    int exportArticle(Article article, HttpServletResponse response) throws Exception;
}
