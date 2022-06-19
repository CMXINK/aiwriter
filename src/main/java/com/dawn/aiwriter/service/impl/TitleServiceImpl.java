package com.dawn.aiwriter.service.impl;

import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.entity.Title;
import com.dawn.aiwriter.mapper.SummaryMapper;
import com.dawn.aiwriter.mapper.TitleMapper;
import com.dawn.aiwriter.service.ITitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class TitleServiceImpl implements ITitleService {

    @Autowired
    private TitleMapper titleMapper;

    @Override
    public List<Title> selectTitleList(Article article) {
        //1、给模型传入正文
        try {
            String articleBody = article.getArticleBody();
            String[] args1 = new String[] { "python", "D:\\test2.py", articleBody};
            Process pr=Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    pr.getInputStream(),"gb2312"));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
            System.out.println("end");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return titleMapper.selectTitleList(article);
    }

    @Override
    public Integer insertTitle(Title title) {
        return titleMapper.insertTitle(title);
    }

    @Override
    public Integer updateTitle(Title title) {
        return titleMapper.updateTitle(title);
    }

    @Override
    public Integer deleteTitle(Long id) {
        return titleMapper.deleteTitle(id);
    }
}
