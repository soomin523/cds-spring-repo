package com.human.cds.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.human.cds.vo.CourseInfoDTO3;


public class ApiExplorerDetail2 {

    public static CourseInfoDTO3 getDetailByContentId(String serviceKey, String contentId, String contentTypeId)
            throws IOException, URISyntaxException {
        
        // API 요청 URL 설정
    	String srcUrl = "https://apis.data.go.kr/B551011/KorService1/detailIntro1";
//        String srcUrl = "https://apis.data.go.kr/B551011/KorService1/detailInfo1";
        StringBuilder urlBuilder = new StringBuilder(srcUrl);
        
        // 필수 파라미터 추가
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + URLEncoder.encode(contentId, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode(contentTypeId, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); // 운영체제 예시 (ETC)
        urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("MyApp", "UTF-8")); // 앱 이름
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); // 응답 형식 JSON

        // URL 생성
        URL url = new URI(urlBuilder.toString()).toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");

        // 응답 처리
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        // 응답을 CourseInfoDTO2로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        CourseInfoDTO3 data = objectMapper.readValue(sb.toString(), CourseInfoDTO3.class);

        return data;
    }
}
