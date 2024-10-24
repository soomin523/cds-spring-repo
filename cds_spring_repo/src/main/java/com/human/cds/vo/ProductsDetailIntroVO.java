package com.human.cds.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

 
 
public class ProductsDetailIntroVO {
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

     
	 
    public static class Items {
        private List<ProductsDetailIntro> item;

		public List<ProductsDetailIntro> getItem() {
			return item;
		}

		public void setItem(List<ProductsDetailIntro> item) {
			this.item = item;
		}
    }

	 
     
    public static class ProductsDetailIntro {
    	private String contentid;	//	콘텐츠ID
    	private String contenttypeid;	//	콘텐츠타입ID
    	
    	//관광지
    	private String accomcount;        		// 수용인원
    	private String chkbabycarriage;        // 유모차대여정보
    	private String chkcreditcard;          // 신용카드가능정보
    	private String chkpet;                 // 애완동물동반가능정보
    	private String expagerange;            // 체험가능연령
    	private String expguide;               // 체험안내
    	private String heritage1;              // 세계문화유산유무
    	private String heritage2;              // 세계자연유산유무
    	private String heritage3;              // 세계기록유산유무
    	private String infocenter;             // 문의및안내
    	private String opendate;               // 개장일
    	private String parking;                // 주차시설
    	private String restdate;               // 쉬는날
    	private String useseason;              // 이용시기
    	private String usetime;                // 이용시간

    	
    	//축제
    	private String agelimit;          	   // 관람가능연령
    	private String bookingplace;           // 예매처
    	private String discountinfofestival;   // 할인정보
    	private String eventenddate;           // 행사종료일
    	private String eventhomepage;          // 행사홈페이지
    	private String eventplace;             // 행사장소
    	private String eventstartdate;         // 행사시작일
    	private String festivalgrade;          // 축제등급
    	private String placeinfo;              // 행사장위치안내
    	private String playtime;               // 공연시간
    	private String program;                // 행사프로그램
    	private String spendtimefestival;      // 관람소요시간
    	private String sponsor1;               // 주최자정보
    	private String sponsor1tel;            // 주최자연락처
    	private String sponsor2;               // 주관사정보
    	private String sponsor2tel;            // 주관사연락처
    	private String subevent;               // 부대행사
    	private String usetimefestival;        // 이용요금

    	
    	//레포츠
		private String accomcountleports; // 수용인원
		private String chkbabycarriageleports; // 유모차대여정보
		private String chkcreditcardleports; // 신용카드가능정보
		private String chkpetleports; // 애완동물동반가능정보
		private String expagerangeleports; // 체험가능연령
		private String infocenterleports; // 문의및안내
		private String openperiod; // 개장기간
		private String parkingfeeleports; // 주차요금
		private String parkingleports; // 주차시설
		private String reservation; // 예약안내 (변경 없음)
		private String restdateleports; // 쉬는날
		private String scaleleports; // 규모
		private String usefeeleports; // 입장료
		private String usetimeleports; // 이용시간
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
		public String getAccomcount() {
			return accomcount;
		}
		public void setAccomcount(String accomcount) {
			this.accomcount = accomcount;
		}
		public String getChkbabycarriage() {
			return chkbabycarriage;
		}
		public void setChkbabycarriage(String chkbabycarriage) {
			this.chkbabycarriage = chkbabycarriage;
		}
		public String getChkcreditcard() {
			return chkcreditcard;
		}
		public void setChkcreditcard(String chkcreditcard) {
			this.chkcreditcard = chkcreditcard;
		}
		public String getChkpet() {
			return chkpet;
		}
		public void setChkpet(String chkpet) {
			this.chkpet = chkpet;
		}
		public String getExpagerange() {
			return expagerange;
		}
		public void setExpagerange(String expagerange) {
			this.expagerange = expagerange;
		}
		public String getExpguide() {
			return expguide;
		}
		public void setExpguide(String expguide) {
			this.expguide = expguide;
		}
		public String getHeritage1() {
			return heritage1;
		}
		public void setHeritage1(String heritage1) {
			this.heritage1 = heritage1;
		}
		public String getHeritage2() {
			return heritage2;
		}
		public void setHeritage2(String heritage2) {
			this.heritage2 = heritage2;
		}
		public String getHeritage3() {
			return heritage3;
		}
		public void setHeritage3(String heritage3) {
			this.heritage3 = heritage3;
		}
		public String getInfocenter() {
			return infocenter;
		}
		public void setInfocenter(String infocenter) {
			this.infocenter = infocenter;
		}
		public String getOpendate() {
			return opendate;
		}
		public void setOpendate(String opendate) {
			this.opendate = opendate;
		}
		public String getParking() {
			return parking;
		}
		public void setParking(String parking) {
			this.parking = parking;
		}
		public String getRestdate() {
			return restdate;
		}
		public void setRestdate(String restdate) {
			this.restdate = restdate;
		}
		public String getUseseason() {
			return useseason;
		}
		public void setUseseason(String useseason) {
			this.useseason = useseason;
		}
		public String getUsetime() {
			return usetime;
		}
		public void setUsetime(String usetime) {
			this.usetime = usetime;
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
		public String getPlaceinfo() {
			return placeinfo;
		}
		public void setPlaceinfo(String placeinfo) {
			this.placeinfo = placeinfo;
		}
		public String getPlaytime() {
			return playtime;
		}
		public void setPlaytime(String playtime) {
			this.playtime = playtime;
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
		public String getAccomcountleports() {
			return accomcountleports;
		}
		public void setAccomcountleports(String accomcountleports) {
			this.accomcountleports = accomcountleports;
		}
		public String getChkbabycarriageleports() {
			return chkbabycarriageleports;
		}
		public void setChkbabycarriageleports(String chkbabycarriageleports) {
			this.chkbabycarriageleports = chkbabycarriageleports;
		}
		public String getChkcreditcardleports() {
			return chkcreditcardleports;
		}
		public void setChkcreditcardleports(String chkcreditcardleports) {
			this.chkcreditcardleports = chkcreditcardleports;
		}
		public String getChkpetleports() {
			return chkpetleports;
		}
		public void setChkpetleports(String chkpetleports) {
			this.chkpetleports = chkpetleports;
		}
		public String getExpagerangeleports() {
			return expagerangeleports;
		}
		public void setExpagerangeleports(String expagerangeleports) {
			this.expagerangeleports = expagerangeleports;
		}
		public String getInfocenterleports() {
			return infocenterleports;
		}
		public void setInfocenterleports(String infocenterleports) {
			this.infocenterleports = infocenterleports;
		}
		public String getOpenperiod() {
			return openperiod;
		}
		public void setOpenperiod(String openperiod) {
			this.openperiod = openperiod;
		}
		public String getParkingfeeleports() {
			return parkingfeeleports;
		}
		public void setParkingfeeleports(String parkingfeeleports) {
			this.parkingfeeleports = parkingfeeleports;
		}
		public String getParkingleports() {
			return parkingleports;
		}
		public void setParkingleports(String parkingleports) {
			this.parkingleports = parkingleports;
		}
		public String getReservation() {
			return reservation;
		}
		public void setReservation(String reservation) {
			this.reservation = reservation;
		}
		public String getRestdateleports() {
			return restdateleports;
		}
		public void setRestdateleports(String restdateleports) {
			this.restdateleports = restdateleports;
		}
		public String getScaleleports() {
			return scaleleports;
		}
		public void setScaleleports(String scaleleports) {
			this.scaleleports = scaleleports;
		}
		public String getUsefeeleports() {
			return usefeeleports;
		}
		public void setUsefeeleports(String usefeeleports) {
			this.usefeeleports = usefeeleports;
		}
		public String getUsetimeleports() {
			return usetimeleports;
		}
		public void setUsetimeleports(String usetimeleports) {
			this.usetimeleports = usetimeleports;
		}
    
		
		
		
    	
    }
}
