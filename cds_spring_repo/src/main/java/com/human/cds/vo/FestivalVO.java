package com.human.cds.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FestivalVO {
	
	private Response response;
	
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}

	@Data
	@NoArgsConstructor
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
	
	@Data
	@NoArgsConstructor
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

	@Data
	@NoArgsConstructor
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
	
	@Data
	@NoArgsConstructor
	public static class Items{
		private List<Festival> item;

		public List<Festival> getItem() {
			return item;
		}
		public void setItem(List<Festival> item) {
			this.item = item;
		}
	
	}
	
	@Data
	@NoArgsConstructor
	public static class Festival{
		private String f_contentid;		//콘텐츠ID
		private String f_firstimage;		//대표이미지(원본)
		private String f_sigungucode; 	//시군구코드
		private String cat1;			//대분류
		private String cat2;			//중분류
		private String cat3;			//소분류
		private String booktour;		//교과서속여행지 여부
		private String f_tel;				//전화번호
		private String f_title;			//제목
		private String f_addr1;			//주소
		private String f_addr2;			//상세주소
		private String f_areacode;		//지역코드
		private String f_mapx;			//GPS X좌표
		private String f_mapy;			//GPS Y좌표
		private String mlevel;			//Map Level
		private String modifiedtime;	//수정일
		private String firstimage2;		//대표이미지(썸네일)
		private String contenttypeid;	//콘텐츠타입ID
		private String createdtime;		//등록일
		private String cpyrhtDivCd;		//저작권 유형 (Type1:제1유형(출처표시-권장), Type3:제3유형(제1유형+변경금지)
		private String f_eventstartdate;	//축제 시작일
		private String f_eventenddate;	//축제마지막일
		private String f_areaname;		//지역이름
		private String f_sigunguname;	//시군구이름
		
		public Festival() {}
		
		public String getF_contentid() {
			return f_contentid;
		}
		public void setF_contentid(String f_contentid) {
			this.f_contentid = f_contentid;
		}
		public String getF_firstimage() {
			return f_firstimage;
		}
		public void setF_firstimage(String f_firstimage) {
			this.f_firstimage = f_firstimage;
		}
		public String getF_sigungucode() {
			return f_sigungucode;
		}
		public void setF_sigungucode(String f_sigungucode) {
			this.f_sigungucode = f_sigungucode;
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
		public String getF_tel() {
			return f_tel;
		}
		public void setF_tel(String f_tel) {
			this.f_tel = f_tel;
		}
		public String getF_title() {
			return f_title;
		}
		public void setF_title(String f_title) {
			this.f_title = f_title;
		}
		public String getF_addr1() {
			return f_addr1;
		}
		public void setF_addr1(String f_addr1) {
			this.f_addr1 = f_addr1;
		}
		public String getF_addr2() {
			return f_addr2;
		}
		public void setF_addr2(String f_addr2) {
			this.f_addr2 = f_addr2;
		}
		public String getF_areacode() {
			return f_areacode;
		}
		public void setF_areacode(String f_areacode) {
			this.f_areacode = f_areacode;
		}
		public String getF_mapx() {
			return f_mapx;
		}
		public void setF_mapx(String f_mapx) {
			this.f_mapx = f_mapx;
		}
		public String getF_mapy() {
			return f_mapy;
		}
		public void setF_mapy(String f_mapy) {
			this.f_mapy = f_mapy;
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
		public String getF_eventstartdate() {
			return f_eventstartdate;
		}
		public void setF_eventstartdate(String f_eventstartdate) {
			this.f_eventstartdate = f_eventstartdate;
		}
		public String getF_eventenddate() {
			return f_eventenddate;
		}
		public void setF_eventenddate(String f_eventenddate) {
			this.f_eventenddate = f_eventenddate;
		}
		public String getF_areaname() {
			return f_areaname;
		}
		public void setF_areaname(String f_areaname) {
			this.f_areaname = f_areaname;
		}
		public String getF_sigunguname() {
			return f_sigunguname;
		}
		public void setF_sigunguname(String f_sigunguname) {
			this.f_sigunguname = f_sigunguname;
		}
	}
	
}
