package com.human.cds.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.human.cds.vo.BusVO;

public class BusApiExplorer {
	 public static <T extends Object> T getApiJsonData(String serviceKey, String srcUrl, String depTerminalId, 
            String arrTerminalId, String depPlandTime, Class<T> vo ) throws Exception {
        
        StringBuilder urlBuilder = new StringBuilder(srcUrl);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=1");
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=2000");
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=json");
        urlBuilder.append("&" + URLEncoder.encode("depTerminalId","UTF-8") + "=" + URLEncoder.encode(depTerminalId, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("arrTerminalId","UTF-8") + "=" + URLEncoder.encode(arrTerminalId, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("depPlandTime","UTF-8") + "=" + URLEncoder.encode(depPlandTime, "UTF-8"));
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
        // 서버에서 받은 데이터를 Jackson API를 이용해서 자바DTO에 세팅하기
        ObjectMapper objectMapper = new ObjectMapper();
        
        // CoercionConfig를 설정하여 빈 문자열을 null로 처리하도록 설정
        objectMapper.coercionConfigFor(BusVO.Items.class)
        	.setCoercion(CoercionInputShape.EmptyString, CoercionAction.AsNull);
        
        // JSON 데이터를 vo 클래스에 바인딩
        T data = objectMapper.readValue(sb.toString(), vo);
        
        return data;
    }
}
