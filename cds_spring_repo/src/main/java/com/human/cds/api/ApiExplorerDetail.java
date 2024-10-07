package com.human.cds.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiExplorerDetail {

    // API 호출 메서드
    public static String get(String apiUrl) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // URL에 연결 설정
        conn.setRequestMethod("GET"); // GET 요청 설정
        conn.setRequestProperty("Content-Type", "application/json"); // 헤더 설정
        conn.setRequestProperty("Accept", "application/json");

        // 응답 코드 확인 (200번이 정상 응답)
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답일 경우
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream())); // 입력 스트림
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine); // 응답 데이터를 result에 추가
            }
            in.close(); // 입력 스트림 종료
        } else {
            System.out.println("GET 요청 실패. 응답 코드: " + responseCode);
        }

        conn.disconnect(); // 연결 종료
        return result.toString(); // JSON 형식의 응답 데이터 반환
    }
}
