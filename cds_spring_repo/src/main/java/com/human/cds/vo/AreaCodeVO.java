package com.human.cds.vo;

import java.util.List;

public class AreaCodeVO {
	
	private Response response;
	
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}

	public static class Response{
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
	
	public static class Header{
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

	public static class Body{
		private int numOfRows;
		private int pageNo;
		private int totalCount;
		private Items items;

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
		public Items getItems() {
			return items;
		}
		public void setItems(Items items) {
			this.items = items;
		}
	}
	
	public static class Items{
		private List<AreaCode> item;

		public List<AreaCode> getItem() {
			return item;
		}
		public void setItem(List<AreaCode> item) {
			this.item = item;
		}
	}
	
	public static class AreaCode{
		private String code; //시군구 코드
		private String name; //시군구명
		private String rnum; //일련번호
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRnum() {
			return rnum;
		}
		public void setRnum(String rnum) {
			this.rnum = rnum;
		}
	}
}
