package com.human.cds.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class AcommointroVO {

	private Response response; // "response" 필드를 추가하여 JSON 응답 구조와 일치시킴

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public static class Response {
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
		private Header header;
		private Body body;
	}

	public static class Header {
		private String resultCode; // API 호출 결과 코드
		private String resultMsg; // API 호출 결과 메시지
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
		private int numOfRows; // 한 페이지에 조회된 데이터 수
		private int totalCount; // 전체 데이터의 총 수
		private int pageNo; // 현재 페이지 번호
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
		private List<Item> item; // item 리스트 (각 코스의 상세 정보)

		public List<Item> getItem() {
			return item;
		}

		public void setItem(List<Item> item) {
			this.item = item;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Item {
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

		public String getRoomtitle() {
			return roomtitle;
		}

		public void setRoomtitle(String roomtitle) {
			this.roomtitle = roomtitle;
		}

		public String getRoomcode() {
			return roomcode;
		}

		public void setRoomcode(String roomcode) {
			this.roomcode = roomcode;
		}

		public String getRoomoffseasonminfee1() {
			return roomoffseasonminfee1;
		}

		public void setRoomoffseasonminfee1(String roomoffseasonminfee1) {
			this.roomoffseasonminfee1 = roomoffseasonminfee1;
		}

		public String getRoomimg1() {
			return roomimg1;
		}

		public void setRoomimg1(String roomimg1) {
			this.roomimg1 = roomimg1;
		}

		public String getRoomimg1alt() {
			return roomimg1alt;
		}

		public void setRoomimg1alt(String roomimg1alt) {
			this.roomimg1alt = roomimg1alt;
		}

		public String getRoomimg2() {
			return roomimg2;
		}

		public void setRoomimg2(String roomimg2) {
			this.roomimg2 = roomimg2;
		}

		public String getRoomimg2alt() {
			return roomimg2alt;
		}

		public void setRoomimg2alt(String roomimg2alt) {
			this.roomimg2alt = roomimg2alt;
		}

		public String getRoomimg3() {
			return roomimg3;
		}

		public void setRoomimg3(String roomimg3) {
			this.roomimg3 = roomimg3;
		}

		public String getRoomimg3alt() {
			return roomimg3alt;
		}

		public void setRoomimg3alt(String roomimg3alt) {
			this.roomimg3alt = roomimg3alt;
		}

		public String getRoomimg4() {
			return roomimg4;
		}

		public void setRoomimg4(String roomimg4) {
			this.roomimg4 = roomimg4;
		}

		public String getRoomimg4alt() {
			return roomimg4alt;
		}

		public void setRoomimg4alt(String roomimg4alt) {
			this.roomimg4alt = roomimg4alt;
		}

		public String getRoomimg5() {
			return roomimg5;
		}

		public void setRoomimg5(String roomimg5) {
			this.roomimg5 = roomimg5;
		}

		public String getRoomimg5alt() {
			return roomimg5alt;
		}

		public void setRoomimg5alt(String roomimg5alt) {
			this.roomimg5alt = roomimg5alt;
		}

		private String contentid; // 콘텐츠 ID
		private String contenttypeid; // 콘텐츠 타입 ID
		private String roomtitle;
		private String roomcode;
		private String roomoffseasonminfee1;
		private String roomimg1;
		private String roomimg1alt;
		private String roomimg2;
		private String roomimg2alt;
		private String roomimg3;
		private String roomimg3alt;
		private String roomimg4;
		private String roomimg4alt;
		private String roomimg5;
		private String roomimg5alt;
	}
}
