package com.human.cds.vo;


import java.util.List;

public class FestivalModalVO {
	
	//FestivalDBVO
	private String f_contentid;		//콘텐츠ID
	private String f_firstimage;	//대표이미지(원본)
	private String f_sigungucode; 	//시군구코드
	private String f_tel;			//전화번호
	private String f_title;			//제목
	private String f_addr1;			//주소
	private String f_addr2;			//상세주소
	private String f_areacode;		//지역코드
	private String f_mapx;			//GPS X좌표
	private String f_mapy;			//GPS Y좌표
	private String f_eventstartdate;//축제 시작일
	private String f_eventenddate;	//축제마지막일
	private String f_areaname;		//지역이름
	private String f_sigunguname;	//시군구이름
	//FestivalImgVO
	private List<String> originimgurl; 	//이미지
	//DetailCommonVO
	private String overview;		//콘텐츠개요
	//FDetailIntroVO
	private String bookingplace;	//예매처
	private String usetimefestival; //이용요금
	private String discountinfofestival; //할인정보
	private String eventhomepage; 	//행사홈페이지
	private String eventplace; 		//헹사장소
	private String program;			//행사프로그램
	private String sponsor1; 		//주최자정보
	private String sponsor2;		//주관사정보
	private String sponsor1tel;		//주최자연락처
	
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
	public List<String> getOriginimgurl() {
		return originimgurl;
	}
	public void setOriginimgurl(List<String> originimgurl) {
		this.originimgurl = originimgurl;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getBookingplace() {
		return bookingplace;
	}
	public void setBookingplace(String bookingplace) {
		this.bookingplace = bookingplace;
	}
	public String getUsetimefestival() {
		return usetimefestival;
	}
	public void setUsetimefestival(String usetimefestival) {
		this.usetimefestival = usetimefestival;
	}
	public String getDiscountinfofestival() {
		return discountinfofestival;
	}
	public void setDiscountinfofestival(String discountinfofestival) {
		this.discountinfofestival = discountinfofestival;
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
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getSponsor1() {
		return sponsor1;
	}
	public void setSponsor1(String sponsor1) {
		this.sponsor1 = sponsor1;
	}
	public String getSponsor2() {
		return sponsor2;
	}
	public void setSponsor2(String sponsor2) {
		this.sponsor2 = sponsor2;
	}
	public String getSponsor1tel() {
		return sponsor1tel;
	}
	public void setSponsor1tel(String sponsor1tel) {
		this.sponsor1tel = sponsor1tel;
	}
	
}
