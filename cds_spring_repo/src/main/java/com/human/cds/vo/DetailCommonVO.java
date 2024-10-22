package com.human.cds.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DetailCommonVO {
    private Response response;

    public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}

	@Data
    @NoArgsConstructor
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

    @Data
    @NoArgsConstructor
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

    @Data
    @NoArgsConstructor
    public static class Body {
        private Items items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
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
    }

    @Data
    @NoArgsConstructor
    public static class Items {
        private List<Item> item;
		public List<Item> getItem() {
			return item;
		}
		public void setItem(List<Item> item) {
			this.item = item;
		}
    }

    @Data
    @NoArgsConstructor
    public static class Item {
        private String contentid;		//콘텐츠ID
        private String contenttypeid;	//콘텐츠타입ID
        private String overview;		//콘텐츠개요
        private String booktour;
        private String createdtime;
        private String homepage;
        private String modifiedtime;
        private String tel;
        private String telname;
        private String title;
        
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
		public String getHomepage() {
			return homepage;
		}
		public void getHomepage(String homepage) {
			this.homepage = homepage;
		}
    }
}
