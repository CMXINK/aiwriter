    package com.dawn.aiwriter.controller;

    import com.alibaba.fastjson.JSONObject;
    import com.dawn.aiwriter.common.utils.HttpUtils;
    import com.dawn.aiwriter.entity.Article;
    import com.dawn.aiwriter.entity.Summary;
    import com.dawn.aiwriter.entity.Title;
    import com.dawn.aiwriter.service.ISummaryService;
    import com.dawn.aiwriter.service.ITitleService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.util.LinkedMultiValueMap;
    import org.springframework.util.MultiValueMap;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.client.RestTemplate;

    import java.util.ArrayList;
    import java.util.List;

    @RestController
    @RequestMapping("/title")
    public class TitleController {
        @Autowired
        private ITitleService titleService;
        @Autowired
        RestTemplate restTemplate;


        @GetMapping("/list")
        public List<Title> listTitle(Article article){
            String url = "http://127.0.0.1:5555/news-title-generate";
            String content = article.getArticleBody();
            Title title = new Title();
            MultiValueMap<String,Object> paramMap = new LinkedMultiValueMap<>();
            paramMap.add("content",content);
            List<String> titleList = restTemplate.postForObject(url,paramMap,List.class);
            Long articleId = article.getArticleId();
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            System.out.println(result);
//            HttpUtils httpUtils = new HttpUtils();
//            ResponseEntity<JSONObject> titleList = httpUtils.doPost(url,paramMap);
            titleService.deleteTitle(articleId);
            for (int i = 0; i < titleList.size(); i++) {
                String titleContent = titleList.get(i);
                title.setTitle(titleContent);
                title.setArticleId(articleId);
                title.setStatus('0');
                titleService.insertTitle(title);
            }
            List<Title> list = titleService.selectTitleList(article);
            return list;
        }
        @PostMapping("/add")
        public int addTitle(Title title){
            Integer result = titleService.insertTitle(title);
            return result;
        }
        @PostMapping("/update")
        public int updateTitle(Title title){
            Integer result = titleService.updateTitle(title);
            return result;
        }
        @DeleteMapping("/{id}")
        public int deleteTitle(@PathVariable Long id){
            Integer result = titleService.deleteTitle(id);
            return result;
        }
    }
