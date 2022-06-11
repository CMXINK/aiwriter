package com.dawn.aiwriter.controller;

import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 文件的新增、删除、上传、下载、查看等处理
 * @author Dawn Johnson
 * @date 2022/5/28 7:47
 * @version 1.0
 */
@RestController
@RequestMapping("/article")
public class ArticleController{

    @Autowired
    private IArticleService articleService;

    /**
     * @description: 查询文章信息列表
     * @param: null
     * @return: 文章信息列表
     */
    @GetMapping("/list")
    public List<Article> listArticle(){
        List<Article> list = articleService.selectArticleList();
        return list;
    }

    @GetMapping(value = "/{id}")
    public Article getArticleById(@PathVariable("id")Long id) {
        Article result = articleService.selectArticleById(id);

        return result;
    }

    @PostMapping("/add")
    public int addArticle(Article article){
        Integer result = articleService.insertArticle(article);
        return result;
    }

    @PostMapping("/update")
    public int updateArticle(Article article){
        Integer result = articleService.updateArticle(article);
        return result;
    }

    @DeleteMapping("/{id}")
    public int deleteArticle(@PathVariable Long id){
        Integer result = articleService.deleteArticle(id);
        return result;
    }

}
