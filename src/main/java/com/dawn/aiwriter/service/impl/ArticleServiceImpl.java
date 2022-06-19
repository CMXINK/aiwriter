package com.dawn.aiwriter.service.impl;

import com.dawn.aiwriter.entity.Article;
import com.dawn.aiwriter.mapper.ArticleMapper;
import com.dawn.aiwriter.mapper.SummaryMapper;
import com.dawn.aiwriter.mapper.TitleMapper;
import com.dawn.aiwriter.service.IArticleService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private SummaryMapper summaryMapper;

    @Autowired
    private TitleMapper titleMapper;

    @Override
    public List<Article> selectArticleList() {
        return articleMapper.selectArticleList();
    }

    @Override
    public Integer insertArticle(Article article) {
        int wordNum = 0;
        article.setWordNum(wordNum);
        return articleMapper.insertArticle(article);
    }

    /**
     * @description: TODO 这里删除还有问题，当删除出错时，没进行错误处理。
     * @author Dawn Johnson
     * @date 2022/6/14
     * @version 1.0
     */
    @Override
    public Integer deleteArticle(Long id) {
        int articleResult = articleMapper.deleteArticle(id);
        int titleResult = titleMapper.deleteTitle(id);
        int summaryResult = summaryMapper.deleteSummary(id);
        return articleResult;
    }

    @Override
    public Integer updateArticle(Article article) {
        String articleBody = article.getArticleBody();
        int wordNum = countWords(articleBody);
        article.setWordNum(wordNum);
        return articleMapper.updateArticle(article);
    }

    @Override
    public Article selectArticleById(Long id) {
        return articleMapper.selectArticleById(id);
    }

    /**
     * @description: TODO 上传失败的时候没有进行处理，也没有给前端返回正确的内容。
     * @author Dawn Johnson
     * @date 2022/6/15 21:39
     * @version 1.0
     */
    @Override
    public String importArticle(MultipartFile file) {
        InputStream fis = null;
        Article article = new Article();
        try {
            String content = "";
            if(!file.isEmpty()) {
                if(file.getSize() > 20971520) {
                    return "";
                }
                fis =  file.getInputStream();
                String fileName = file.getOriginalFilename();
                if(fileName.endsWith(".doc")) {
                    @SuppressWarnings("resource")
                    WordExtractor extractor = new WordExtractor(fis);
                    content = extractor.getText();
                } else if(fileName.endsWith(".docx")) {
                    XWPFDocument document = new XWPFDocument(fis);
                    @SuppressWarnings("resource")
                    XWPFWordExtractor extractor = new XWPFWordExtractor(document);
                    content = extractor.getText();
                } else if(fileName.endsWith(".txt")) {
                    content = IOUtils.toString(fis, Charset.defaultCharset());
                } else {
                    return "";
                }
            }
            article.setArticleBody(content);
            //TODO 这里应该还有个判断，如果插入数据库失败时，返回啥？
            insertArticle(article);
            return content;
        }catch(Exception e) {
            return "";
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int exportArticle(Article article, HttpServletResponse response) throws Exception{
        //读取数据库的内容
        String articleBody = article.getArticleBody();
        Long articleId = article.getArticleId();
//        File file = new File("D:/aiwriter/uploadPath" + '/'+articleId);
        XWPFDocument document= new XWPFDocument();
//        try {
//            FileOutputStream out = new FileOutputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        XWPFParagraph firstParagraph = document.createParagraph();
        XWPFRun run = firstParagraph.createRun();
        run.setText(articleBody);
        String fileName = "word导出" + System.currentTimeMillis() + ".doc";
        String fileNameURL = null;
        fileNameURL = URLEncoder.encode(fileName, "UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileNameURL + ";" + "filename*=utf-8''" + fileNameURL);
        response.setContentType("application/octet-stream");
        //刷新缓冲
        response.flushBuffer();
        OutputStream ouputStream = response.getOutputStream();
        document.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
        return 0;
    }

    /**
     * @description: 现在只是计算中文，TODO 英文后面再说。
     * @author Dawn Johnson
     * @date 2022/6/13
     * @version 1.0
     */
    public Integer countWords(String articleBody) {
        if (articleBody.isEmpty()) {
            return 0;
        }
        //获取正文
        //统计汉字个数方法一
        //TODO 这样写有bug,就是当乱输入时，会把别的字符也当成正文
        //String cnWords = articleBody.replaceAll("[^(\\u4e00-\\u9fa5\\x3130-\\x318F\\u0800-\\u4e00，。《》？；’‘：“”【】、）（……￥！·)]", "");
        //int cnWordsCount = cnWords.length();
        //解决方法：就是通过match判断是不是中文就行，是true就count++。
        int cnCount = 0;
        for (int i = 0; i < articleBody.length(); i++) {
            boolean isChinese = Pattern.matches("^[\u4E00-\u9Fa5]{0,}$", articleBody.charAt(i)+"");
            if(isChinese){
                cnCount ++;
            }
        }
        return cnCount;
//          英文字符处理
//          思路：只判断这个单词是不是单词，如果是就count++
//        String[] enWords = articleBody.split("[,.; !]");
//
//        return null;
     }

}
