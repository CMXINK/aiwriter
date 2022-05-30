package com.dawn.aiwriter.mapper;

import com.dawn.aiwriter.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {
    List<Article> selectArticleList();

    Integer insertArticle(Article article);

    Integer deleteArticle(Long id);

    Integer updateArticle(Article article);
}
