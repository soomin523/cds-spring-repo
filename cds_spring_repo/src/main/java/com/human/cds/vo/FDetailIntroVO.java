package com.human.cds.vo;

import java.util.List;

public class FDetailIntroVO {
	
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
		private int totalCount;
		private int pageNo;
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
	
	public static class Items{
		private List<DetailIntro> item;
		
		public List<DetailIntro> getItem() {
			return item;
		}
		public void setItem(List<DetailIntro> item) {
			this.item = item;
		}

	}
	
	public static class DetailIntro{
		private String discountinfofestival; 	//행사공연축제(15) : 할인정보
		private String eventenddate; 			//행사공연축제(15) : 행사종료일
		private String playtime; 				//행사공연축제(15) : 공연시간
		private String eventhomepage; 			//행사공연축제(15) : 행사홈페이지
		private String eventplace; 				//행사공연축제(15) : 행사장소
		private String eventstartdate; 			//행사공연축제(15) : 행사시작일
		private String festivalgrade;			//행사공연축제(15) : 축제등급
		private String program; 				//행사공연축제(15) : 행사프로그램
		private String spendtimefestival;		//행사공연축제(15) : 관람소요시간
		private String sponsor1; 				//행사공연축제(15) : 주최자정보
		private String sponsor1tel; 			//행사공연축제(15) : 주최자연락처
		private String agelimit; 				//행사공연축제(15) : 관람가능연령
		private String bookingplace;			//행사공연축제(15) : 예매처
		private String sponsor2;			 	//행사공연축제(15) : 주관사정보
		private String sponsor2tel;			 	//행사공연축제(15) : 주관사연락처
		private String subevent;			 	//행사공연축제(15) : 부대행사
		private String usetimefestival;			//행사공연축제(15) : 이용요금
		private String placeinfo;			 	//행사공연축제(15) : 행사장위치안내
		private String contentid;			 	//기본정보 : 콘텐츠ID
		private String contenttypeid;			//기본정보 : 콘텐츠타입ID
		
		public String getDiscountinfofestival() {
			return discountinfofestival;
		}
		public void setDiscountinfofestival(String discountinfofestival) {
			this.discountinfofestival = discountinfofestival;
		}
		public String getEventenddate() {
			return eventenddate;
		}
		public void setEventenddate(String eventenddate) {
			this.eventenddate = eventenddate;
		}
		public String getPlaytime() {
			return playtime;
		}
		public void setPlaytime(String playtime) {
			this.playtime = playtime;
		}
		public String getEventhomepage() {
			return eventhomepage;
		}
		public void setEventhomepage(String eventhomepage) {
			this.eventhomepage = eventhomepage;
		}
		public String getEventplace() {
			return eventplace;
		}
		public void setEventplace(String eventplace) {
			this.eventplace = eventplace;
		}
		public String getEventstartdate() {
			return eventstartdate;
		}
		public void setEventstartdate(String eventstartdate) {
			this.eventstartdate = eventstartdate;
		}
		public String getFestivalgrade() {
			return festivalgrade;
		}
		public void setFestivalgrade(String festivalgrade) {
			this.festivalgrade = festivalgrade;
		}
		public String getProgram() {
			return program;
		}
		public void setProgram(String program) {
			this.program = program;
		}
		public String getSpendtimefestival() {
			return spendtimefestival;
		}
		public void setSpendtimefestival(String spendtimefestival) {
			this.spendtimefestival = spendtimefestival;
		}
		public String getSponsor1() {
			return sponsor1;
		}
		public void setSponsor1(String sponsor1) {
			this.sponsor1 = sponsor1;
		}
		public String getSponsor1tel() {
			return sponsor1tel;
		}
		public void setSponsor1tel(String sponsor1tel) {
			this.sponsor1tel = sponsor1tel;
		}
		public String getAgelimit() {
			return agelimit;
		}
		public void setAgelimit(String agelimit) {
			this.agelimit = agelimit;
		}
		public String getBookingplace() {
			return bookingplace;
		}
		public void setBookingplace(String bookingplace) {
			this.bookingplace = bookingplace;
		}
		public String getSponsor2() {
			return sponsor2;
		}
		public void setSponsor2(String sponsor2) {
			this.sponsor2 = sponsor2;
		}
		public String getSponsor2tel() {
			return sponsor2tel;
		}
		public void setSponsor2tel(String sponsor2tel) {
			this.sponsor2tel = sponsor2tel;
		}
		public String getSubevent() {
			return subevent;
		}
		public void setSubevent(String subevent) {
			this.subevent = subevent;
		}
		public String getUsetimefestival() {
			return usetimefestival;
		}
		public void setUsetimefestival(String usetimefestival) {
			this.usetimefestival = usetimefestival;
		}
		public String getPlaceinfo() {
			return placeinfo;
		}
		public void setPlaceinfo(String placeinfo) {
			this.placeinfo = placeinfo;
		}
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
	}
	
}
