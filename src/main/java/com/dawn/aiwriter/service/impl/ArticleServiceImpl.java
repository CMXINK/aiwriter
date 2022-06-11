package com.dawn.aiwriter.service.impl;

import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.mapper.ArticleMapper;
import com.dawn.aiwriter.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    @Override
    public Article selectArticleById(Long id) {
        return articleMapper.selectArticleById(id);
    }

    @Override
    public Integer countWords(Article article) {
        if (article.getWordNum() == null) {
            return 0;
        }
        //获取正文
        String articleBody = article.getArticleBody();
        //统计汉字个数方法一
        //TODO 这样写有bug,就是当乱输入时，会把别的字符也当成正文
        //String cnWords = articleBody.replaceAll("[^(\\u4e00-\\u9fa5\\x3130-\\x318F\\u0800-\\u4e00，。《》？；’‘：“”【】、）（……￥！·)]", "");
        //int cnWordsCount = cnWords.length();
        //解决方法：就是通过match判断是不是中文就行，是true就count++。
        int cnCount = 0;
        for (int i = 0; i < articleBody.length(); i++) {
            boolean isChinese = Pattern.matches("^[\u4E00-\u9Fa5]{0,}$", articleBody.charAt(i)+"");
            if(isChinese){
                cnCount ++;
            }
        }
        //英文字符处理
        //思路：只判断这个单词是不是单词，如果是就count++
        String[] enWords = articleBody.split("[,.; !]");

        return null;
    }

}
