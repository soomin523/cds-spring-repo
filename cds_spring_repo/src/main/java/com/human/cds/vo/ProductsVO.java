package com.human.cds.vo;

import java.util.List;

	//지역기반 관광정보조회
 
 
public class ProductsVO {
    private Response response;

	 
	 
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
        private List<Products> item;

		public List<Products> getItem() {
			return item;
		}

		public void setItem(List<Products> item) {
			this.item = item;
		}
    }

	 
     
    public static class Products {
    	private String addr1;          // 주소
        private String addr2;          // 상세주소
        private String homepage;
        private String areacode;       // 지역코드
        private String areaname;       // 지역이름
        private String booktour;       // 교과서속여행지 여부
        private String cat1;           // 대분류
        private String cat2;           // 중분류
        private String cat3;           // 소분류
        private String contentid;      // 콘텐츠ID
        private String contenttypeid;  // 콘텐츠타입ID
        private String createdtime;    // 등록일
        private String firstimage;     // 대표이미지(원본)
        private String firstimage2;    // 대표이미지(썸네일)
        private String cpyrhtDivCd;    // 저작권 유형
        private String mapx;           // GPS X좌표
        private String mapy;           // GPS Y좌표
        private String mlevel;         // Map Level
        private String readcount;
        private String modifiedtime;   // 수정일
        private String sigungucode;    // 시군구코드
        private String tel;            // 전화번호
        private String title;          // 제목
        private String zipcode;        // 우편번호
        private String info;        // 
        private String opendate;        // 
        private String enddate;        // 
        private String restdate;        // 
        private String price;        // 
        private String usetime;        // 
        private String overview;        // 
        private String infoname1;
        private String infotext1;
        private String infoname2;
        private String infotext2;
        private String infoname3;
        private String infotext3;
        private String infoname4;
        private String infotext4;
        private String infoname5;
        private String infotext5;
        private String infoname6;
        private String infotext6;
        private String infoname7;
        private String infotext7;
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
		public String getHomepage() {
			return homepage;
		}
		public void setHomepage(String homepage) {
			this.homepage = homepage;
		}
		public String getAreacode() {
			return areacode;
		}
		public void setAreacode(String areacode) {
			this.areacode = areacode;
		}
		public String getAreaname() {
			return areaname;
		}
		public void setAreaname(String areaname) {
			this.areaname = areaname;
		}
		public String getBooktour() {
			return booktour;
		}
		public void setBooktour(String booktour) {
			this.booktour = booktour;
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
		public String getCreatedtime() {
			return createdtime;
		}
		public void setCreatedtime(String createdtime) {
			this.createdtime = createdtime;
		}
		public String getFirstimage() {
			return firstimage;
		}
		public void setFirstimage(String firstimage) {
			this.firstimage = firstimage;
		}
		public String getFirstimage2() {
			return firstimage2;
		}
		public void setFirstimage2(String firstimage2) {
			this.firstimage2 = firstimage2;
		}
		public String getCpyrhtDivCd() {
			return cpyrhtDivCd;
		}
		public void setCpyrhtDivCd(String cpyrhtDivCd) {
			this.cpyrhtDivCd = cpyrhtDivCd;
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
		public String getReadcount() {
			return readcount;
		}
		public void setReadcount(String readcount) {
			this.readcount = readcount;
		}
		public String getModifiedtime() {
			return modifiedtime;
		}
		public void setModifiedtime(String modifiedtime) {
			this.modifiedtime = modifiedtime;
		}
		public String getSigungucode() {
			return sigungucode;
		}
		public void setSigungucode(String sigungucode) {
			this.sigungucode = sigungucode;
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
		public String getZipcode() {
			return zipcode;
		}
		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public String getOpendate() {
			return opendate;
		}
		public void setOpendate(String opendate) {
			this.opendate = opendate;
		}
		public String getEnddate() {
			return enddate;
		}
		public void setEnddate(String enddate) {
			this.enddate = enddate;
		}
		public String getRestdate() {
			return restdate;
		}
		public void setRestdate(String restdate) {
			this.restdate = restdate;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getUsetime() {
			return usetime;
		}
		public void setUsetime(String usetime) {
			this.usetime = usetime;
		}
		public String getOverview() {
			return overview;
		}
		public void setOverview(String overview) {
			this.overview = overview;
		}
		public String getInfoname1() {
			return infoname1;
		}
		public void setInfoname1(String infoname1) {
			this.infoname1 = infoname1;
		}
		public String getInfotext1() {
			return infotext1;
		}
		public void setInfotext1(String infotext1) {
			this.infotext1 = infotext1;
		}
		public String getInfoname2() {
			return infoname2;
		}
		public void setInfoname2(String infoname2) {
			this.infoname2 = infoname2;
		}
		public String getInfotext2() {
			return infotext2;
		}
		public void setInfotext2(String infotext2) {
			this.infotext2 = infotext2;
		}
		public String getInfoname3() {
			return infoname3;
		}
		public void setInfoname3(String infoname3) {
			this.infoname3 = infoname3;
		}
		public String getInfotext3() {
			return infotext3;
		}
		public void setInfotext3(String infotext3) {
			this.infotext3 = infotext3;
		}
		public String getInfoname4() {
			return infoname4;
		}
		public void setInfoname4(String infoname4) {
			this.infoname4 = infoname4;
		}
		public String getInfotext4() {
			return infotext4;
		}
		public void setInfotext4(String infotext4) {
			this.infotext4 = infotext4;
		}
		public String getInfoname5() {
			return infoname5;
		}
		public void setInfoname5(String infoname5) {
			this.infoname5 = infoname5;
		}
		public String getInfotext5() {
			return infotext5;
		}
		public void setInfotext5(String infotext5) {
			this.infotext5 = infotext5;
		}
		public String getInfoname6() {
			return infoname6;
		}
		public void setInfoname6(String infoname6) {
			this.infoname6 = infoname6;
		}
		public String getInfotext6() {
			return infotext6;
		}
		public void setInfotext6(String infotext6) {
			this.infotext6 = infotext6;
		}
		public String getInfoname7() {
			return infoname7;
		}
		public void setInfoname7(String infoname7) {
			this.infoname7 = infoname7;
		}
		public String getInfotext7() {
			return infotext7;
		}
		public void setInfotext7(String infotext7) {
			this.infotext7 = infotext7;
		}

        

    	
    }

	
}
