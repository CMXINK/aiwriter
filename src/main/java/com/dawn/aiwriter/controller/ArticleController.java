package com.dawn.aiwriter.controller;

import com.dawn.aiwriter.common.utils.HttpUtils;
import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.Filer;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.FileUtils;

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

    /**
     * @description: 新增文章 TODO 没有进行插入失败时判断。
     * @param: Article
     * @return: 文章id
     */
    @PostMapping("/add")
    public Long addArticle(Article article){
        Integer result = articleService.insertArticle(article);
        Long articleId = article.getArticleId();
        return articleId;
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

    /**
     * @description: 文章下载
     * @param: null
     * @return:
     */
    @GetMapping("/export")
    public int exportArticle(Article article, HttpServletResponse response) throws Exception {
        int result = articleService.exportArticle(article,response);
        return result;
    }

    /**
     * @description: 文章上传
     * @param: null
     * @return:
     */
    @PostMapping("/import")
    public String importArticle(@RequestParam("file") MultipartFile file){
        String result = articleService.importArticle(file);
        return result;
    }
}
