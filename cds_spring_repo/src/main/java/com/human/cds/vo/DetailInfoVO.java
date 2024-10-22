package com.human.cds.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

				//반복정보조회
@Data
@NoArgsConstructor
public class DetailInfoVO {
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
        private List<DetailInfo> item;
    }

	@Data
    @NoArgsConstructor
    public static class DetailInfo {
    	private String contentid; //	콘텐츠ID
    	private String contenttypeid; //	콘텐츠타입ID
    	private String fldgubun; // 일련번호 
    	private String infoname; // 제목 
    	private String infotext; // 내용 
    	private String serialnum; // 반복일련번호 

    
    	
    }
}
