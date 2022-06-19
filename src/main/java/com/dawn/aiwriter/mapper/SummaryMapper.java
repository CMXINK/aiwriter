package com.dawn.aiwriter.mapper;

import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.entity.Summary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SummaryMapper {
    List<Summary> selectSummaryList(Article article);

    Integer insertSummary(Summary summary);

    Integer updateSummary(Summary summary);

    Integer deleteSummary(Long id);
}
