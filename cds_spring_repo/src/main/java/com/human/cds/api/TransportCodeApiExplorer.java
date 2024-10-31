package com.human.cds.api;


	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

	public class TransportCodeApiExplorer {
		
	    public static <T extends Object> T getApiJsonData(String serviceKey,
	    													String srcUrl,
	    													Class<T> vo ) throws IOException, URISyntaxException {

	        StringBuilder urlBuilder = new StringBuilder(srcUrl); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=1");
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=2000"); // Increased to 1000
	        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*데이터 타입(xml, json)*/
	        
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
	        
	        
	        // JSON 데이터를 vo 클래스에 바인딩
	        T data = objectMapper.readValue(sb.toString(), vo);
	        
	        return data;
	    }
	}

