package com.human.cds.vo;

import lombok.Data;
import java.util.List;

@Data
public class DestinationIntroVO {
    private Response response;

    public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}

	@Data
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
    	private String	contentid	;	//	콘텐츠ID
    	private String	contenttypeid	;	//	콘텐츠타입ID
    	private String	accomcount	;	//	수용인원
    	private String	chkbabycarriage	;	//	유모차대여정보
    	private String	chkcreditcard	;	//	신용카드가능정보
    	private String	chkpet	;	//	애완동물동반가능정보
    	private String	expagerange	;	//	체험가능연령
    	private String	expguide	;	//	체험안내
    	private String	heritage1	;	//	세계문화유산유무
    	private String	heritage2	;	//	세계자연유산유무
    	private String	heritage3	;	//	세계기록유산유무
    	private String	infocenter	;	//	문의및안내
    	private String	opendate	;	//	개장일
    	private String	parking	;	//	주차시설
    	private String	restdate	;	//	쉬는날
    	private String	useseason	;	//	이용시기
    	private String	usetime	;	//	이용시간
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
    
    
    	
    }
}
