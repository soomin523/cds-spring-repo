package com.human.cds.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseInfoDTO3 {

    private Response response;  // "response" 필드를 추가하여 JSON 응답 구조와 일치시킴

    @Data
    @NoArgsConstructor
    public static class Response {
        private Header header;
        private Body body;
    }

    @Data
    @NoArgsConstructor
    public static class Header {
        private String resultCode;  // API 호출 결과 코드
        private String resultMsg;   // API 호출 결과 메시지
    }

    @Data
    @NoArgsConstructor
    public static class Body {
        private int numOfRows;     // 한 페이지에 조회된 데이터 수
        private int totalCount;    // 전체 데이터의 총 수
        private int pageNo;        // 현재 페이지 번호
        private Items items;
    }

    @Data
    @NoArgsConstructor
    public static class Items {
        private List<Item> item;   // item 리스트 (각 코스의 상세 정보)
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String contentid;       // 콘텐츠 ID
        private String contenttypeid;   // 콘텐츠 타입 ID
        private String distance;        // 거리
        private String taketime;        // 소요 시간
    }
}
