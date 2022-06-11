package com.dawn.aiwriter.service;

import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.entity.Title;

import java.util.List;

public interface ITitleService {
    public List<Title> selectTitleList(Article article);

    Integer insertTitle(Title title);
}
