package com.dawn.aiwriter.service;

import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.entity.Summary;

import java.util.List;

public interface ISummaryService {
    public List<Summary> selectSummaryList(Article article);

    Integer insertSummary(Summary summary);

    Integer updateSummary(Summary summary);

    Integer deleteSummary(Long id);
}
