package com.dawn.aiwriter.service.impl;

import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.entity.Title;
import com.dawn.aiwriter.mapper.SummaryMapper;
import com.dawn.aiwriter.mapper.TitleMapper;
import com.dawn.aiwriter.service.ITitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleServiceImpl implements ITitleService {

    @Autowired
    private TitleMapper titleMapper;

    @Override
    public List<Title> selectTitleList(Article article) {
        return titleMapper.selectTitleList(article);
    }

    @Override
    public Integer insertTitle(Title title) {
        return titleMapper.insertTitle(title);
    }
}
