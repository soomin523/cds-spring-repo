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
import com.human.cds.vo.CourseInfoDTO;
import com.human.cds.vo.CourseInfoDTO2;

public class ApiExplorerCourseinfo {

	public static CourseInfoDTO getApiJsonData(String serviceKey, String srcUrl, String pageNo, String numOfRows, String contenttypeid)
	        throws IOException, URISyntaxException {
	    StringBuilder urlBuilder = new StringBuilder(srcUrl);
	    urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
	    urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
	    urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));
	    urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
	    urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("MyApp", "UTF-8"));
	    urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
	    urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode(contenttypeid, "UTF-8"));

	    URL url = new URI(urlBuilder.toString()).toURL();
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-type", "application/json");
	    conn.setRequestProperty("Accept", "application/json");

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

	    ObjectMapper objectMapper = new ObjectMapper();
	    CourseInfoDTO data = objectMapper.readValue(sb.toString(), CourseInfoDTO.class);

	    return data;
	}

}
