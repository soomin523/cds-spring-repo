package com.human.cds.vo;

import java.util.List;

public class FestivalVO {
	
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
		private List<Festival> item;

		public List<Festival> getItem() {
			return item;
		}
		public void setItem(List<Festival> item) {
			this.item = item;
		}
	
	}
	
	public static class Festival{
		private String contentid;		//콘텐츠ID
		private String firstimage;		//대표이미지(원본)
		private String sigungucode; 	//시군구코드
		private String cat1;			//대분류
		private String cat2;			//중분류
		private String cat3;			//소분류
		private String booktour;		//교과서속여행지 여부
		private String tel;				//전화번호
		private String title;			//제목
		private String addr1;			//주소
		private String addr2;			//상세주소
		private String areacode;		//지역코드
		private String mapx;			//GPS X좌표
		private String mapy;			//GPS Y좌표
		private String mlevel;			//Map Level
		private String modifiedtime;	//수정일
		private String firstimage2;		//대표이미지(썸네일)
		private String contenttypeid;	//콘텐츠타입ID
		private String createdtime;		//등록일
		private String cpyrhtDivCd;		//저작권 유형 (Type1:제1유형(출처표시-권장), Type3:제3유형(제1유형+변경금지)
		private String eventstartdate;	//축제 시작일
		private String eventenddate;	//축제마지막일
		
		public Festival() {}
		
		public String getContentid() {
			return contentid;
		}
		public void setContentid(String contentid) {
			this.contentid = contentid;
		}
		public String getFirstimage() {
			return firstimage;
		}
		public void setFirstimage(String firstimage) {
			this.firstimage = firstimage;
		}
		public String getSigungucode() {
			return sigungucode;
		}
		public void setSigungucode(String sigungucode) {
			this.sigungucode = sigungucode;
		}
		public String getCat1() {
			return cat1;
		}
		public void setCat1(String cat1) {
			this.cat1 = cat1;
		}
		public String getCat2() {
			return cat2;
		}
		public void setCat2(String cat2) {
			this.cat2 = cat2;
		}
		public String getCat3() {
			return cat3;
		}
		public void setCat3(String cat3) {
			this.cat3 = cat3;
		}
		public String getBooktour() {
			return booktour;
		}
		public void setBooktour(String booktour) {
			this.booktour = booktour;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getAddr1() {
			return addr1;
		}
		public void setAddr1(String addr1) {
			this.addr1 = addr1;
		}
		public String getAddr2() {
			return addr2;
		}
		public void setAddr2(String addr2) {
			this.addr2 = addr2;
		}
		public String getAreacode() {
			return areacode;
		}
		public void setAreacode(String areacode) {
			this.areacode = areacode;
		}
		public String getMapx() {
			return mapx;
		}
		public void setMapx(String mapx) {
			this.mapx = mapx;
		}
		public String getMapy() {
			return mapy;
		}
		public void setMapy(String mapy) {
			this.mapy = mapy;
		}
		public String getMlevel() {
			return mlevel;
		}
		public void setMlevel(String mlevel) {
			this.mlevel = mlevel;
		}
		public String getModifiedtime() {
			return modifiedtime;
		}
		public void setModifiedtime(String modifiedtime) {
			this.modifiedtime = modifiedtime;
		}
		public String getFirstimage2() {
			return firstimage2;
		}
		public void setFirstimage2(String firstimage2) {
			this.firstimage2 = firstimage2;
		}
		public String getContenttypeid() {
			return contenttypeid;
		}
		public void setContenttypeid(String contenttypeid) {
			this.contenttypeid = contenttypeid;
		}
		public String getCreatedtime() {
			return createdtime;
		}
		public void setCreatedtime(String createdtime) {
			this.createdtime = createdtime;
		}
		public String getCpyrhtDivCd() {
			return cpyrhtDivCd;
		}
		public void setCpyrhtDivCd(String cpyrhtDivCd) {
			this.cpyrhtDivCd = cpyrhtDivCd;
		}
		public String getEventstartdate() {
			return eventstartdate;
		}
		public void setEventstartdate(String eventstartdate) {
			this.eventstartdate = eventstartdate;
		}
		public String getEventenddate() {
			return eventenddate;
		}
		public void setEventenddate(String eventenddate) {
			this.eventenddate = eventenddate;
		}
	}
	
}
