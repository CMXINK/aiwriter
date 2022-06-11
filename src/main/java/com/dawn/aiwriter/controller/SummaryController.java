package com.dawn.aiwriter.controller;

import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.entity.Summary;
import com.dawn.aiwriter.service.ISummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/summary")
public class SummaryController {
    @Autowired
    private ISummaryService summaryService;

    @GetMapping("/list")
    public List<Summary> listSummary(Article article){
        List<Summary> list = summaryService.selectSummaryList(article);
        return list;
    }
    @PostMapping("/add")
    public int addSummary(Summary summary){
        Integer result = summaryService.insertSummary(summary);
        return result;
    }


}
