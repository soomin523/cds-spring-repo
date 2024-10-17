package com.human.cds.vo;

import java.util.List;



public class CourseInfoDTO2 {
    private Response response;

    
    
    public Response getResponse() {
		return response;
	}



	public void setResponse(Response response) {
		this.response = response;
	}



	public static class Response {
        private Header header;
        private Body body;
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
    }

    
    
    public static class Header {
        private String resultCode;
        private String resultMsg;
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
    }

    
    
    public static class Body {
        private Items items;
        private int numOfRows;
        private int pageNo;
        public Items getItems() {
			return items;
		}
		public void setItems(Items items) {
			this.items = items;
		}
		public int getNumOfRows() {
			return numOfRows;
		}
		public void setNumOfRows(int numOfRows) {
			this.numOfRows = numOfRows;
		}
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
		private int totalCount;
    }

    
    
    public static class Items {
        private List<Item> item;

		public List<Item> getItem() {
			return item;
		}

		public void setItem(List<Item> item) {
			this.item = item;
		}
    }

    
    
    public static class Item {
        private String contentid;
        private String contenttypeid;
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
		public String getOverview() {
			return overview;
		}
		public void setOverview(String overview) {
			this.overview = overview;
		}
		private String overview;  // overview 필드 추가
    }
}
