package com.dawn.aiwriter.service.impl;

import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.mapper.ArticleMapper;
import com.dawn.aiwriter.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> selectArticleList() {
        return articleMapper.selectArticleList();
    }

    @Override
    public Integer insertArticle(Article article) {
        return articleMapper.insertArticle(article);
    }

    @Override
    public Integer deleteArticle(Long id) {
        return articleMapper.deleteArticle(id);
    }

    @Override
    public Integer updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }
}
