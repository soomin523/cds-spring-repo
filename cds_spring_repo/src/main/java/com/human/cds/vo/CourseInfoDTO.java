package com.human.cds.vo;

import java.util.List;



public class CourseInfoDTO {
	private Response response;

    
    
    public Response getResponse() {
		return response;
	}



	public void setResponse(Response response) {
		this.response = response;
	}



	public static class Response {
        private Header header;
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
		private Body body;
    }

    
    
    public static class Header {
        private String resultCode;
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
		private String resultMsg;
    }

    
    
    public static class Body {
        private Items items;
        private int numOfRows;
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
		private int pageNo;
        private int totalCount;
    }

    
    
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
        private String addr1;
        private String addr2;
        private String areacode;
        private String booktour;
        private String cat1;
        private String cat2;
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
		private String cat3;
        private String contentid;
        private String contenttypeid;
        private String createdtime;
        private String firstimage;
        private String firstimage2;
        private String cpyrhtDivCd;
        private String mapx;
        private String mapy;
        private String mlevel;
        private String modifiedtime;
        private String sigungucode;
        private String tel;
        private String title;
        private String zipcode;
    }
}
