package com.dawn.aiwriter.service.impl;

import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.entity.Summary;
import com.dawn.aiwriter.mapper.SummaryMapper;
import com.dawn.aiwriter.service.ISummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryServiceImpl implements ISummaryService{

    @Autowired
    private SummaryMapper summaryMapper;

    @Override
    public List<Summary> selectSummaryList(Article article) {
        return summaryMapper.selectSummaryList(article);
    }

    @Override
    public Integer insertSummary(Summary summary) {
        return summaryMapper.insertSummary(summary);
    }
}
