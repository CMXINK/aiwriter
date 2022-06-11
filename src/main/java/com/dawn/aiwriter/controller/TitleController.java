package com.dawn.aiwriter.controller;

import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.entity.Summary;
import com.dawn.aiwriter.entity.Title;
import com.dawn.aiwriter.service.ISummaryService;
import com.dawn.aiwriter.service.ITitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/title")
public class TitleController {
    @Autowired
    private ITitleService titleService;

    @GetMapping("/list")
    public List<Title> listTitle(Article article){
        List<Title> list = titleService.selectTitleList(article);
        return list;
    }
    @PostMapping("/add")
    public int addTitle(Title title){
        Integer result = titleService.insertTitle(title);
        return result;
    }
}