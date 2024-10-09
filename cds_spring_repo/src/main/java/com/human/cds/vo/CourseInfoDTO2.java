package com.human.cds.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseInfoDTO2 {
    private Response response;

    @Data
    @NoArgsConstructor
    public static class Response {
        private Header header;
        private Body body;
    }

    @Data
    @NoArgsConstructor
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Data
    @NoArgsConstructor
    public static class Body {
        private Items items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }

    @Data
    @NoArgsConstructor
    public static class Items {
        private List<Item> item;
    }

    @Data
    @NoArgsConstructor
    public static class Item {
        private String contentid;
        private String contenttypeid;
        private String overview;  // overview 필드 추가
    }
}
