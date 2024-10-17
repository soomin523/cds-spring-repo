package com.human.cds.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



public class CourseInfoDTO3 {

    private Response response;  // "response" 필드를 추가하여 JSON 응답 구조와 일치시킴

    
    
    public Response getResponse() {
		return response;
	}



	public void setResponse(Response response) {
		this.response = response;
	}



	public static class Response {
        private Header header;
        public Header getHeader() {
			return header;
		}
		public void setHeader(Header header) {
			this.header = header;
		}
		public Body getBody() {
			return body;
		}
		public void setBody(Body body) {
			this.body = body;
		}
		private Body body;
    }

    
    
    public static class Header {
        private String resultCode;  // API 호출 결과 코드
        public String getResultCode() {
			return resultCode;
		}
		public void setResultCode(String resultCode) {
			this.resultCode = resultCode;
		}
		public String getResultMsg() {
			return resultMsg;
		}
		public void setResultMsg(String resultMsg) {
			this.resultMsg = resultMsg;
		}
		private String resultMsg;   // API 호출 결과 메시지
    }

    
    
    public static class Body {
        private int numOfRows;     // 한 페이지에 조회된 데이터 수
        private int totalCount;    // 전체 데이터의 총 수
        private int pageNo;        // 현재 페이지 번호
        private Items items;
		public int getNumOfRows() {
			return numOfRows;
		}
		public void setNumOfRows(int numOfRows) {
			this.numOfRows = numOfRows;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		public Items getItems() {
			return items;
		}
		public void setItems(Items items) {
			this.items = items;
		}
    }

    
    
    public static class Items {
        private List<Item> item;   // item 리스트 (각 코스의 상세 정보)

		public List<Item> getItem() {
			return item;
		}

		public void setItem(List<Item> item) {
			this.item = item;
		}
    }

    
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String contentid;       // 콘텐츠 ID
        public String getContentid() {
			return contentid;
		}
		public void setContentid(String contentid) {
			this.contentid = contentid;
		}
		public String getContenttypeid() {
			return contenttypeid;
		}
		public void setContenttypeid(String contenttypeid) {
			this.contenttypeid = contenttypeid;
		}
		public String getDistance() {
			return distance;
		}
		public void setDistance(String distance) {
			this.distance = distance;
		}
		public String getTaketime() {
			return taketime;
		}
		public void setTaketime(String taketime) {
			this.taketime = taketime;
		}
		private String contenttypeid;   // 콘텐츠 타입 ID
        private String distance;        // 거리
        private String taketime;        // 소요 시간
    }
}
