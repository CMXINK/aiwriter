package com.dawn.aiwriter.mapper;

import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.entity.Title;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TitleMapper {
    public List<Title> selectTitleList(Article article);

    public Integer insertTitle(Title title);
}
