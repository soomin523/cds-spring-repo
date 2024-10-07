package com.human.cds.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseInfoDTO {
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
        private String addr1;
        private String addr2;
        private String areacode;
        private String booktour;
        private String cat1;
        private String cat2;
        private String cat3;
        private String contentid;
        private String contenttypeid;
        private String createdtime;
        private String firstimage;
        private String firstimage2;
        private String cpyrhtDivCd;
        private String mapx;
        private String mapy;
        private String mlevel;
        private String modifiedtime;
        private String sigungucode;
        private String tel;
        private String title;
        private String zipcode;
    }
}
