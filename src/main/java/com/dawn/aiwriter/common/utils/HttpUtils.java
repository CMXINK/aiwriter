package com.dawn.aiwriter.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class HttpUtils {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 以post方式请求第三方http接口 postForEntity
     * @param url
     * @return
     */
    public ResponseEntity<JSONObject> doPost(String url, MultiValueMap<String, Object> content){
        ResponseEntity<JSONObject> body = restTemplate.postForEntity(url, content, JSONObject.class);
        return body;
    }
}
